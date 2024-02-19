package com.FKFabian.medicalclinic.exceptions;

import org.springframework.http.HttpStatus;

public class VisitNotFoundException extends MedicalException {
    public VisitNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public VisitNotFoundException(String message) {
        super(message);
    }
}
