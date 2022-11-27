package com.si.rategateway.cotroller;

import com.si.rategateway.cotroller.request.XmlCommandRequest;
import com.si.rategateway.cotroller.response.XmlCommandResponse;
import com.si.rategateway.cotroller.response.XmlCommandResponseList;
import com.si.rategateway.infrastructure.dto.XmlCommandDto;
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

@RestController
@RequestMapping(path = "xml_api/")
@AllArgsConstructor
public class XmlRateController {

    private final RateService rateService;

    @PostMapping(path = "command", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public XmlCommandResponseList requestCurrentCurrencyInfo(@RequestBody XmlCommandRequest request) {
        XmlCommandDto xmlCommandDto = RateMapper.mapXmlCommandDto(request);
        XmlCommandResponseList xmlCommandResponseList = new XmlCommandResponseList();
        List<XmlCommandResponse> responses = rateService.commandCurrency(xmlCommandDto).stream()
                .map(RateMapper::mapToXmlCommandResponse)
                .collect(Collectors.toList());
        xmlCommandResponseList.setResponses(responses);
        return xmlCommandResponseList;
    }
}
