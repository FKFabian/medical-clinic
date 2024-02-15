package com.FKFabian.medicalclinic.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MedicalException extends RuntimeException {
    private HttpStatus httpStatus;

    public MedicalException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public MedicalException(String message) {
      super(message);
    }
}
