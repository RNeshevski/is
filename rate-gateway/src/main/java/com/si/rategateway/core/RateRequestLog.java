package com.si.rategateway.core;

import com.si.rategateway.infrastructure.enums.ExternalServiceType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

import static com.si.rategateway.infrastructure.constants.RateConstants.DEFAULT_ID_GENERATOR_NAME;

@Getter
@Setter
@Entity
@Table(name = "rate_request_log")
@SequenceGenerator(name = DEFAULT_ID_GENERATOR_NAME, sequenceName = "rate_request_logs_sequence", allocationSize = 1)
public class RateRequestLog extends BaseEntity {

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "client_id")
    private Long clientId;

    @Column
    private Timestamp timestamp;

    @Column
    @Enumerated(EnumType.STRING)
    private ExternalServiceType externalServiceType;

    @Column
    private String currency;

    @Column(name = "request_payload")
    private String requestPayload;



}
