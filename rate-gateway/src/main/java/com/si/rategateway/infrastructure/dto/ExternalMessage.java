package com.si.rategateway.infrastructure.dto;

import com.si.rategateway.infrastructure.enums.MessageType;
import lombok.Data;

@Data
public class ExternalMessage {
    private String messageId;
    private MessageType messageType;
    private String body;
}
