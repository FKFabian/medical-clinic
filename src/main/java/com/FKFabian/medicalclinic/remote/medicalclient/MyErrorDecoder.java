package com.FKFabian.medicalclinic.remote.medicalclient;

import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class MyErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultError = new Default();

    @Override
    public Exception decode(String s, Response response) {
        FeignException exception = feign.FeignException.errorStatus(s, response);
        if (response.status() == 503) {
            return new RetryableException(
                    response.status(),
                    exception.getMessage(),
                    response.request().httpMethod(),
                    exception,
                    null,
                    response.request());
        }
        return defaultError.decode(s, response);
    }
}
