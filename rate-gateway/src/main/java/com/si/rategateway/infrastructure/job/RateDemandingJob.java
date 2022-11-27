package com.si.rategateway.infrastructure.job;

import com.si.rategateway.config.MessagingConfig;
import com.si.rategateway.core.Rate;
import com.si.rategateway.infrastructure.client.FixerClient;
import com.si.rategateway.infrastructure.dto.ExternalMessage;
import com.si.rategateway.infrastructure.dto.FixerRateResponse;
import com.si.rategateway.service.RateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.si.rategateway.infrastructure.mapper.RateMapper.mapToExternalMessage;
import static com.si.rategateway.infrastructure.mapper.RateMapper.mapToRatesList;

@Component
@AllArgsConstructor
@Slf4j
public class RateDemandingJob {

    private final FixerClient fixerClient;
    private final RateService rateService;
    private final MessagingConfig messagingConfig;
    private final RabbitTemplate template;

    @Scheduled(fixedDelayString = "#{jobConfig.delay}")
    public void demandRates() {
        try {
            FixerRateResponse fixerRateResponse = fixerClient.getRates();
            List<Rate> rateList = mapToRatesList(fixerRateResponse);
            publishMessage(fixerRateResponse);
            rateService.saveRates(rateList);
        } catch (Exception e) {
            log.error("There was unexpected error while getting rates!", e);
        }
    }

    private void publishMessage(FixerRateResponse fixerRateResponse) {
        ExternalMessage externalMessage = mapToExternalMessage(fixerRateResponse);
        template.convertAndSend(messagingConfig.getExchange(), messagingConfig.getRoutingKey(), externalMessage);
        log.info(externalMessage.toString());
    }
}
