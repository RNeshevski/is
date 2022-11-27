package com.si.rategateway.infrastructure.dto;

import lombok.Data;

@Data
public class JsonCurrencyHistoryRequestDto extends JsonCurrencyRequestDto {
    private int period;
}
