package com.si.rategateway.infrastructure.dto;

import com.si.rategateway.infrastructure.enums.ExternalServiceType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Getter
@Setter
@ToString
public class XmlCommandDto {
    private String requestId;
    private Long clientId;
    private Timestamp timestamp;
    private ExternalServiceType externalServiceType;
    private String currency;
    private int period;
}
