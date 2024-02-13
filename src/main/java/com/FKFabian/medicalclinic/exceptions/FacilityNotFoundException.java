package com.FKFabian.medicalclinic.exceptions;

import org.springframework.http.HttpStatus;

public class FacilityNotFoundException extends MedicalExceptions{
    public FacilityNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public FacilityNotFoundException(String message) {
        super(message);
    }
}
