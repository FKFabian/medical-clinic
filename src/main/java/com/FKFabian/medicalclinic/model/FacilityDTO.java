package com.FKFabian.medicalclinic.model;

import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class FacilityDTO {
    private final String name;
    private final String city;
    private final String zipCode;
    private final String street;
    private final String noBuilding;
    private final List<Long> doctorsId;
}
