package com.si.rategateway.cotroller;

import com.si.rategateway.cotroller.request.JsonCurrencyHistoryRequest;
import com.si.rategateway.cotroller.request.JsonCurrencyRequest;
import com.si.rategateway.cotroller.response.JsonCurrencyResponse;
import com.si.rategateway.infrastructure.dto.JsonCurrencyHistoryRequestDto;
import com.si.rategateway.infrastructure.dto.JsonCurrencyRequestDto;
import com.si.rategateway.infrastructure.mapper.RateMapper;
import com.si.rategateway.service.RateService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.si.rategateway.infrastructure.mapper.RateMapper.mapToJsonCurrencyResponse;

@RestController
@RequestMapping(path = "json_api/")
@AllArgsConstructor
public class JsonRateController {

    private final RateService rateService;

    @PostMapping(path = "current", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonCurrencyResponse requestCurrentCurrencyInfo(@RequestBody JsonCurrencyRequest request) {
        JsonCurrencyRequestDto currencyRequestDto = RateMapper.mapJsonCurrencyRequestDto(request);
        return mapToJsonCurrencyResponse(rateService.requestCurrency(currencyRequestDto));
    }

    @PostMapping(path = "history", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JsonCurrencyResponse> requestHistoryCurrencyInfo(@RequestBody JsonCurrencyHistoryRequest request) {
        JsonCurrencyHistoryRequestDto currencyHistoryRequestDto = RateMapper.mapJsonCurrencyHistoryRequestDto(request);
        return rateService.requestHistoryCurrency(currencyHistoryRequestDto).stream()
                .map(RateMapper::mapToJsonCurrencyResponse)
                .collect(Collectors.toList());
    }
}
