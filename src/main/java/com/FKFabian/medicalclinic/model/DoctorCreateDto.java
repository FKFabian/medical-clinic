package com.FKFabian.medicalclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class DoctorCreateDto {
    private final String email;
    private final String password;
    private final String name;
    private final String surname;
    private final String specialization;
}
