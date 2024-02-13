package com.FKFabian.medicalclinic.common;

import com.FKFabian.medicalclinic.model.DoctorCreateDto;
import com.FKFabian.medicalclinic.model.FacilityCreateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FacilityValidator {

    public static void checkIfAnyFacilitiesValueIsNull(FacilityCreateDto facilityCreateDto) {
        if (facilityCreateDto.getName() == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (facilityCreateDto.getCity() == null) {
            throw new IllegalArgumentException("City cannot be null");
        }
        if (facilityCreateDto.getZipCode() == null) {
            throw new IllegalArgumentException("Zip code cannot be null");
        }
        if (facilityCreateDto.getStreet() == null) {
            throw new IllegalArgumentException("Street cannot be null");
        }
        if (facilityCreateDto.getNoBuilding() == null) {
            throw new IllegalArgumentException("Building number cannot be null");
        }

    }
}
