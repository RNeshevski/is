package com.si.rategateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class RateGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(RateGatewayApplication.class, args);
    }

}
