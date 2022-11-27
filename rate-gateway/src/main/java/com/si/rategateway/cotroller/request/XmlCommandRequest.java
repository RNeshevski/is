package com.si.rategateway.cotroller.request;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "command")
@XmlAccessorType(XmlAccessType.NONE)
public class XmlCommandRequest {
    @XmlAttribute(name="id")
    private String requestId;
    @XmlElement(name="get")
    private GetDto getDto;
    @XmlElement(name="history")
    private HistoryDto historyDto;
}
