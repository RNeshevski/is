package com.si.rategateway.cotroller.request;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "history")
@XmlAccessorType(XmlAccessType.NONE)
public class HistoryDto {

    @XmlAttribute(name="consumer")
    private Long consumer;

    @XmlAttribute(name="currency")
    private String currency;

    @XmlAttribute(name="period")
    private int period;
}
