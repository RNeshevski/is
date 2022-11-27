package com.si.rategateway.service;

import com.si.rategateway.config.MessagingConfig;
import com.si.rategateway.core.Rate;
import com.si.rategateway.infrastructure.dto.ExternalMessage;
import com.si.rategateway.infrastructure.dto.JsonCurrencyHistoryRequestDto;
import com.si.rategateway.infrastructure.dto.JsonCurrencyRequestDto;
import com.si.rategateway.infrastructure.dto.XmlCommandDto;
import com.si.rategateway.repository.RateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.si.rategateway.infrastructure.mapper.RateMapper.mapToExternalMessage;
import static java.time.ZoneOffset.UTC;

@Service
@AllArgsConstructor
@Slf4j
public class RateServiceImpl implements RateService {

    private final RateLogService rateLogService;
    private final RateRepository rateRepository;
    private final RabbitTemplate template;
    private final MessagingConfig messagingConfig;


    @Override
    public void saveRates(List<Rate> rates) {
        rateRepository.saveAll(rates);
    }

    @Override
    public Rate requestCurrency(JsonCurrencyRequestDto currencyRequestDto) {
        String requestId = currencyRequestDto.getRequestId();
        rateLogService.isExisting(requestId);
        Rate rate = rateRepository.findTopByCurrencyOrderByCreatedAtDesc(currencyRequestDto.getCurrency());
        rateLogService.saveJsonLog(currencyRequestDto);
        publishMessage(mapToExternalMessage(currencyRequestDto));
        return rate;
    }

    @Override
    public List<Rate> requestHistoryCurrency(JsonCurrencyHistoryRequestDto currencyHistoryRequestDto) {
        String requestId = currencyHistoryRequestDto.getRequestId();
        rateLogService.isExisting(requestId);
        List<Rate> rates = rateRepository.findByCurrencyAndCreatedAtAfterOrderByCreatedAt(currencyHistoryRequestDto.getCurrency(),
                LocalDateTime.now(UTC).minusHours(currencyHistoryRequestDto.getPeriod()));
        rateLogService.saveJsonLog(currencyHistoryRequestDto);
        publishMessage(mapToExternalMessage(currencyHistoryRequestDto));
        return rates;
    }

    @Override
    public List<Rate> commandCurrency(XmlCommandDto xmlCommandDto) {
        String requestId = xmlCommandDto.getRequestId();
        String currency = xmlCommandDto.getCurrency();
        rateLogService.isExisting(requestId);
        List<Rate> rates = new ArrayList<>();

        if (xmlCommandDto.getPeriod() == 0) {
            rates.add(rateRepository.findTopByCurrencyOrderByCreatedAtDesc(currency));
        } else {
            rates = rateRepository.findByCurrencyAndCreatedAtAfterOrderByCreatedAt(currency,
                    LocalDateTime.now(UTC).minusHours(xmlCommandDto.getPeriod()));
        }
        rateLogService.saveXmlLog(xmlCommandDto);
        publishMessage(mapToExternalMessage(xmlCommandDto));
        return rates;
    }

    private void publishMessage(ExternalMessage externalMessage) {
        template.convertAndSend(messagingConfig.getExchange(), messagingConfig.getRoutingKey(), externalMessage);
        log.info(externalMessage.toString());
    }


}
