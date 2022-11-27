package com.si.rategateway.cotroller.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class JsonCurrencyRequest {
    @JsonAlias("id")
    private UUID requestId;
    private Timestamp timestamp;
    private Long client;
    private String currency;
}
