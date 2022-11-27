package com.si.rategateway.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class JsonCurrencyHistoryRequestDto extends JsonCurrencyRequestDto {
    private int period;
}
