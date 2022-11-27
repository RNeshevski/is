package com.si.rategateway.cotroller.request;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "get")
@XmlAccessorType(XmlAccessType.NONE)
public class GetDto {

    @XmlAttribute(name="consumer")
    private Long consumer;

    @XmlElement(name="currency")
    private String currency;
}
