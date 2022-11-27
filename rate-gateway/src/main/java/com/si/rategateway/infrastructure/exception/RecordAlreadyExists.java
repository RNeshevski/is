package com.si.rategateway.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class RecordAlreadyExists extends RuntimeException {
    public RecordAlreadyExists(String message) {
        super(message);
    }
}
