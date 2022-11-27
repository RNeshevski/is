package com.si.rategateway.cotroller.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class JsonCurrencyResponse {
    private String currency;
    private BigDecimal conversionRate;
    private String base;
    private Long timestamp;
}
