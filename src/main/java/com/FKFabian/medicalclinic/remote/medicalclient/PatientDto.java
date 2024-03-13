package com.FKFabian.medicalclinic.remote.medicalclient;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDto {
    private final String email;
    private final String idCardNo;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final LocalDate birthday;
}
