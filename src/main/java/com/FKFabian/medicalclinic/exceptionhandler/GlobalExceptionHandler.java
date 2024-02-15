package com.FKFabian.medicalclinic.exceptionhandler;

import com.FKFabian.medicalclinic.exceptions.DoctorNotFoundException;
import com.FKFabian.medicalclinic.exceptions.FacilityNotFoundException;
import com.FKFabian.medicalclinic.exceptions.MedicalExceptions;
import com.FKFabian.medicalclinic.exceptions.ObjectAlreadyExistException;
import com.FKFabian.medicalclinic.exceptions.PatientNotFoundException;
import com.FKFabian.medicalclinic.message.Message;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Message handlerIllegalArgumentException(IllegalArgumentException exception) {
        return new Message(exception.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Message handlerNotFoundPatientException(PatientNotFoundException exception) {
        return new Message(exception.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Message handlerDoctorNotFoundException(PatientNotFoundException exception) {
        return new Message(exception.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FacilityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Message handlerFacilityNotFoundException(PatientNotFoundException exception) {
        return new Message(exception.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Message handlerObjectAlreadyExistException(ObjectAlreadyExistException exception) {
        return new Message(exception.getMessage(), LocalDateTime.now(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MedicalExceptions.class)
    public ResponseEntity<Message> handlerMedicalExceptions(MedicalExceptions exceptions) {
        return ResponseEntity
                .status(exceptions.getHttpStatus())
                .body(new Message(exceptions.getMessage(), LocalDateTime.now(), exceptions.getHttpStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Message handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new Message(exception.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Message handleConstraintViolationException(ConstraintViolationException exception) {
        return new Message(exception.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST);
    }
}
