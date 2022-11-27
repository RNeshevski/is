package com.si.rategateway.repository;

import com.si.rategateway.core.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    Rate findTopByCurrencyOrderByCreatedAtDesc(String currency);

    List<Rate> findByCurrencyAndCreatedAtAfterOrderByCreatedAt(String currency, LocalDateTime time);
}
