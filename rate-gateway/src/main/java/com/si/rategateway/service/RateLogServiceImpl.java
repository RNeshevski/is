package com.si.rategateway.service;

import com.si.rategateway.core.RateRequestLog;
import com.si.rategateway.infrastructure.dto.JsonCurrencyRequestDto;
import com.si.rategateway.infrastructure.dto.XmlCommandDto;
import com.si.rategateway.infrastructure.exception.RecordAlreadyExists;
import com.si.rategateway.repository.RateLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.si.rategateway.infrastructure.mapper.RateMapper.mapToRateRequestLog;

@Service
@AllArgsConstructor
public class RateLogServiceImpl implements RateLogService {

    private final RateLogRepository rateLogRepository;

    @Override
    public void saveJsonLog(JsonCurrencyRequestDto jsonCurrencyRequestDto) {
        RateRequestLog rateRequestLog = mapToRateRequestLog(jsonCurrencyRequestDto);
        rateLogRepository.save(rateRequestLog);
    }

    @Override
    public void saveXmlLog(XmlCommandDto xmlCommandDto) {
        RateRequestLog rateRequestLog = mapToRateRequestLog(xmlCommandDto);
        rateLogRepository.save(rateRequestLog);
    }

    @Override
    public void isExisting(String requestId) {
        if (rateLogRepository.existsByRequestId(requestId)) {
            throw new RecordAlreadyExists(String.format("Record with requestId: %s already exists", requestId));
        }
    }
}
