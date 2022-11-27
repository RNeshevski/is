package com.si.rategateway.infrastructure.mapper;

import com.si.rategateway.core.Rate;
import com.si.rategateway.cotroller.request.*;
import com.si.rategateway.core.RateRequestLog;
import com.si.rategateway.cotroller.response.JsonCurrencyResponse;
import com.si.rategateway.cotroller.response.XmlCommandResponse;
import com.si.rategateway.infrastructure.dto.*;
import com.si.rategateway.infrastructure.enums.ExternalServiceType;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.si.rategateway.infrastructure.enums.MessageType.JOB;
import static com.si.rategateway.infrastructure.enums.MessageType.REQUEST;
import static java.time.ZoneOffset.UTC;

@NoArgsConstructor
public class RateMapper {
    public static List<Rate> mapToRatesList(FixerRateResponse input) {

        List<Rate> rateList = new ArrayList<>();
        if (Objects.nonNull(input)) {
            input.getRates().forEach((currency, rate) -> {
                Rate rateToSave = new Rate();
                rateToSave.setDate(input.getDate());
                rateToSave.setCurrency(currency);
                rateToSave.setTimestamp(input.getTimestamp());
                rateToSave.setConversionRate(rate);
                rateToSave.setBase(input.getBase());
                rateList.add(rateToSave);
            });
        }
        return rateList;
    }

    public static JsonCurrencyRequestDto mapJsonCurrencyRequestDto(JsonCurrencyRequest input) {
        if (Objects.isNull(input)){
            return null;
        }
        JsonCurrencyRequestDto currencyRequestDto = new JsonCurrencyRequestDto();
        currencyRequestDto.setRequestId(input.getRequestId().toString());
        currencyRequestDto.setClientId(input.getClient());
        currencyRequestDto.setTimestamp(input.getTimestamp());
        currencyRequestDto.setExternalServiceType(ExternalServiceType.EXT_SERVICE_1);
        currencyRequestDto.setCurrency(input.getCurrency());

        return currencyRequestDto;
    }
    public static JsonCurrencyHistoryRequestDto mapJsonCurrencyHistoryRequestDto(JsonCurrencyHistoryRequest input) {
        if (Objects.isNull(input)){
            return null;
        }
        JsonCurrencyHistoryRequestDto currencyHistoryRequestDto = new JsonCurrencyHistoryRequestDto();
        currencyHistoryRequestDto.setRequestId(input.getRequestId().toString());
        currencyHistoryRequestDto.setClientId(input.getClient());
        currencyHistoryRequestDto.setTimestamp(input.getTimestamp());
        currencyHistoryRequestDto.setExternalServiceType(ExternalServiceType.EXT_SERVICE_1);
        currencyHistoryRequestDto.setCurrency(input.getCurrency());
        currencyHistoryRequestDto.setPeriod(input.getPeriod());

        return currencyHistoryRequestDto;
    }

    public static XmlCommandDto mapXmlCommandDto(XmlCommandRequest input) {
        if (Objects.isNull(input)){
            return null;
        }
        XmlCommandDto xmlCommandDto = new XmlCommandDto();
        xmlCommandDto.setRequestId(input.getRequestId());
        xmlCommandDto.setTimestamp(Timestamp.valueOf(LocalDateTime.now(UTC)));
        xmlCommandDto.setExternalServiceType(ExternalServiceType.EXT_SERVICE_2);
        GetDto getDto = input.getGetDto();
        HistoryDto historyDto = input.getHistoryDto();
        if (Objects.nonNull(getDto)){
            xmlCommandDto.setClientId(getDto.getConsumer());
            xmlCommandDto.setCurrency(getDto.getCurrency());
        }else{
            xmlCommandDto.setCurrency(historyDto.getCurrency());
            xmlCommandDto.setClientId(historyDto.getConsumer());
            xmlCommandDto.setPeriod(historyDto.getPeriod());
        }
        return xmlCommandDto;
    }

    public static RateRequestLog mapToRateRequestLog(JsonCurrencyRequestDto input) {
        if (Objects.isNull(input)){
            return null;
        }
        RateRequestLog rateRequestLog = new RateRequestLog();
        rateRequestLog.setRequestId(input.getRequestId());
        rateRequestLog.setClientId(input.getClientId());
        rateRequestLog.setTimestamp(input.getTimestamp());
        rateRequestLog.setExternalServiceType(input.getExternalServiceType());
        rateRequestLog.setCurrency(input.getCurrency());
        rateRequestLog.setRequestPayload(input.toString());

        return rateRequestLog;
    }

    public static RateRequestLog mapToRateRequestLog(XmlCommandDto input) {
        if (Objects.isNull(input)){
            return null;
        }
        RateRequestLog rateRequestLog = new RateRequestLog();
        rateRequestLog.setRequestId(input.getRequestId());
        rateRequestLog.setClientId(input.getClientId());
        rateRequestLog.setTimestamp(input.getTimestamp());
        rateRequestLog.setExternalServiceType(input.getExternalServiceType());
        rateRequestLog.setCurrency(input.getCurrency());
        rateRequestLog.setRequestPayload(input.toString());

        return rateRequestLog;
    }

    public static ExternalMessage mapToExternalMessage(FixerRateResponse input){
        if (Objects.isNull(input)){
            return null;
        }
        ExternalMessage externalMessage= new ExternalMessage();
        externalMessage.setMessageId(UUID.randomUUID().toString());
        externalMessage.setMessageType(JOB);
        externalMessage.setBody(input.toString());
        return externalMessage;
    }

    public static ExternalMessage mapToExternalMessage(JsonCurrencyRequestDto input){
        if (Objects.isNull(input)){
            return null;
        }
        ExternalMessage externalMessage= new ExternalMessage();
        externalMessage.setMessageId(input.getRequestId());
        externalMessage.setMessageType(REQUEST);
        externalMessage.setBody(input.toString());
        return externalMessage;
    }
    public static ExternalMessage mapToExternalMessage(XmlCommandDto input){
        if (Objects.isNull(input)){
            return null;
        }
        ExternalMessage externalMessage= new ExternalMessage();
        externalMessage.setMessageId(input.getRequestId());
        externalMessage.setMessageType(REQUEST);
        externalMessage.setBody(input.toString());
        return externalMessage;
    }

    public static JsonCurrencyResponse mapToJsonCurrencyResponse(Rate input){
        if (Objects.isNull(input)){
            return null;
        }
        JsonCurrencyResponse response = new JsonCurrencyResponse();
        response.setCurrency(input.getCurrency());
        response.setBase(input.getBase());
        response.setConversionRate(input.getConversionRate());
        response.setTimestamp(input.getTimestamp());
        return response;
    }

    public static XmlCommandResponse mapToXmlCommandResponse(Rate input){
        if (Objects.isNull(input)){
            return null;
        }
        XmlCommandResponse response = new XmlCommandResponse();
        response.setCurrency(input.getCurrency());
        response.setBase(input.getBase());
        response.setConversionRate(input.getConversionRate());
        response.setTimestamp(input.getTimestamp());
        return response;
    }
}
