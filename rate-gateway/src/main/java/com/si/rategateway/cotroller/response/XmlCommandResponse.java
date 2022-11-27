package com.si.rategateway.cotroller.response;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Data
@XmlRootElement()
@XmlAccessorType(XmlAccessType.NONE)
public class XmlCommandResponse {
    @XmlElement
    private String currency;
    @XmlElement
    private BigDecimal conversionRate;
    @XmlElement
    private String base;
    @XmlElement
    private Long timestamp;
}
