package com.si.rategateway.infrastructure.client;

import com.si.rategateway.config.RateApiConfig;
import com.si.rategateway.infrastructure.exception.ApiCallException;
import com.si.rategateway.infrastructure.dto.FixerRateResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@Component
public class FixerClient {

    private final RestTemplate restTemplate;
    private final RateApiConfig rateApiConfig;

    public FixerRateResponse getRates() {

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(rateApiConfig.getUrl())
                .queryParam("access_key", rateApiConfig.getAccessKey());

        try {
            return restTemplate.getForObject(builder.toUriString(), FixerRateResponse.class);
        } catch (RestClientResponseException e) {
            throw new ApiCallException(e.getRawStatusCode(), e.getResponseBodyAsString());
        }

    }
}
