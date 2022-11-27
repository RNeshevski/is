package com.si.rategateway.core;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.si.rategateway.infrastructure.constants.RateConstants.DEFAULT_ID_GENERATOR_NAME;
import static java.time.ZoneOffset.UTC;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = DEFAULT_ID_GENERATOR_NAME)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now(UTC);
    }

}
