package com.FKFabian.medicalclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

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
