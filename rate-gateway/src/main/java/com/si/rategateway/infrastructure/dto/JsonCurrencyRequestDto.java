package com.si.rategateway.infrastructure.dto;

import com.si.rategateway.infrastructure.enums.ExternalServiceType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class JsonCurrencyRequestDto {
    private String requestId;
    private Long clientId;
    private Timestamp timestamp;
    private ExternalServiceType externalServiceType;
    private String currency;
}
