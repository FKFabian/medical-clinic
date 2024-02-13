package com.FKFabian.medicalclinic.message;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class Message {
    private final String message;
    private final LocalDateTime localDateTime;
    private final HttpStatus httpStatus;
}
