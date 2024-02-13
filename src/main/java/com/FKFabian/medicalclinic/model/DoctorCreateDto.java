package com.FKFabian.medicalclinic.model;

import lombok.*;

import java.util.List;

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
