package com.FKFabian.medicalclinic.remote.medicalclient;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class ClientConfiguration {
    @Bean
    public Retryer retryer() {
        return new Retryer.Default(100L, TimeUnit.SECONDS.toMillis(5L), 3);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new MyErrorDecoder();
    }
}
