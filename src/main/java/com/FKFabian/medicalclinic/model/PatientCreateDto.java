package com.FKFabian.medicalclinic.model;

import lombok.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PatientCreateDto {
    private final String email;
    private final String password;
    private final String idCardNo;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final LocalDate birthday;
}
