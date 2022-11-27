package com.si.rategateway.repository;

import com.si.rategateway.core.RateRequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateLogRepository extends JpaRepository<RateRequestLog, Long> {
    boolean existsByRequestId(String Id);
}
