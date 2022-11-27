package com.si.rategateway.cotroller.response;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement()
@XmlAccessorType(XmlAccessType.NONE)
public class XmlCommandResponseList {
    @XmlElementWrapper
    List<XmlCommandResponse> xmlCommandResponse;
}
