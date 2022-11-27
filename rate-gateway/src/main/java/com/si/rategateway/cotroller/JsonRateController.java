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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<JsonCurrencyResponse> requestCurrentCurrencyInfo(@RequestBody JsonCurrencyRequest request) {
        JsonCurrencyRequestDto currencyRequestDto = RateMapper.mapJsonCurrencyRequestDto(request);
        JsonCurrencyResponse jsonCurrencyResponse = mapToJsonCurrencyResponse(rateService.requestCurrency(currencyRequestDto));
        return ResponseEntity.ok()
                .body(jsonCurrencyResponse);
    }

    @PostMapping(path = "history", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JsonCurrencyResponse>> requestHistoryCurrencyInfo(@RequestBody JsonCurrencyHistoryRequest request) {
        JsonCurrencyHistoryRequestDto currencyHistoryRequestDto = RateMapper.mapJsonCurrencyHistoryRequestDto(request);
        List<JsonCurrencyResponse> jsonCurrencyResponses = rateService.requestHistoryCurrency(currencyHistoryRequestDto).stream()
                .map(RateMapper::mapToJsonCurrencyResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(jsonCurrencyResponses);
    }
}
