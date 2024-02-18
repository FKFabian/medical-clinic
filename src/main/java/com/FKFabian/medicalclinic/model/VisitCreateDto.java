package com.FKFabian.medicalclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class VisitCreateDto {
    private final LocalDateTime visitDate;
    private final Patient patient;
    private final Doctor doctor;
}
