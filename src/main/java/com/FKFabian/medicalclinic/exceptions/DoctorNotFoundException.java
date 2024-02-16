package com.FKFabian.medicalclinic.exceptions;

import org.springframework.http.HttpStatus;

public class DoctorNotFoundException extends MedicalException{
    public DoctorNotFoundException(String message) {
        super(message);
    }

    public DoctorNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

}
