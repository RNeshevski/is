package com.si.rategateway.service;

import com.si.rategateway.infrastructure.dto.JsonCurrencyRequestDto;
import com.si.rategateway.infrastructure.dto.XmlCommandDto;

public interface RateLogService {
    void saveJsonLog(JsonCurrencyRequestDto jsonCurrencyRequestDto);
    void saveXmlLog(XmlCommandDto xmlCommandDto);
    void isExisting(String requestId);
}
