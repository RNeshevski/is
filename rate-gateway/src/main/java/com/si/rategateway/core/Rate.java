package com.si.rategateway.core;

import com.si.rategateway.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static com.si.rategateway.infrastructure.constants.RateConstants.DEFAULT_ID_GENERATOR_NAME;

@Entity
@Getter
@Setter
@Table(name = "rate")
@SequenceGenerator(name = DEFAULT_ID_GENERATOR_NAME, sequenceName = "rate_sequence", allocationSize = 1)
public class Rate extends BaseEntity {
    @Column
    private Long timestamp;

    @Column
    private String base;

    @Column
    private Date date;

    @Column
    private String currency;

    @Column
    private BigDecimal conversionRate;

}
