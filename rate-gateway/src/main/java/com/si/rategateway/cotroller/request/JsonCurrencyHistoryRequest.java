package com.si.rategateway.cotroller.request;

import lombok.Data;

@Data
public class JsonCurrencyHistoryRequest extends JsonCurrencyRequest {
    private int period;
}
