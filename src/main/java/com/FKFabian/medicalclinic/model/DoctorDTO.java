package com.FKFabian.medicalclinic.model;

import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class DoctorDTO {
    private final String email;
    private final String name;
    private final String surname;
    private final String specialization;
    private final List<Long> facilitiesId;
}


//DTO - reqconstr
//model - noargcost i allargcons