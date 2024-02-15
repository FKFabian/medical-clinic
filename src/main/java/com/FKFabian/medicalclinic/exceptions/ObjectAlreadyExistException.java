package com.FKFabian.medicalclinic.exceptions;

import org.springframework.http.HttpStatus;

public class ObjectAlreadyExistException extends MedicalExceptions{
    public ObjectAlreadyExistException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public ObjectAlreadyExistException(String message) {
        super(message);
    }
}
