package com.FKFabian.medicalclinic.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MedicalExceptions extends RuntimeException {
    private HttpStatus httpStatus;

    public MedicalExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public MedicalExceptions(String message) {
      super(message);
    }
}
