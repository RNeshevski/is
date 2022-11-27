package com.si.rategateway.service;

import com.si.rategateway.core.Rate;
import com.si.rategateway.infrastructure.dto.JsonCurrencyHistoryRequestDto;
import com.si.rategateway.infrastructure.dto.JsonCurrencyRequestDto;
import com.si.rategateway.infrastructure.dto.XmlCommandDto;

import java.util.List;

public interface RateService {
    void saveRates(List<Rate> rates);

    Rate requestCurrency(JsonCurrencyRequestDto jsonCurrencyRequestDto);

    List<Rate> commandCurrency(XmlCommandDto xmlCommandDto);

    List<Rate> requestHistoryCurrency(JsonCurrencyHistoryRequestDto jsonCurrencyHistoryRequestDto);
}
