package com.FKFabian.medicalclinic.exceptions;

import org.springframework.http.HttpStatus;


public class PatientNotFoundException extends MedicalExceptions {
    public PatientNotFoundException(String message) {
        super(message);
    }

    public PatientNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

