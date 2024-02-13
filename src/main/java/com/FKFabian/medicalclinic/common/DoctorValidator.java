package com.FKFabian.medicalclinic.common;

import com.FKFabian.medicalclinic.model.Doctor;
import com.FKFabian.medicalclinic.model.DoctorCreateDto;
import com.FKFabian.medicalclinic.model.Patient;
import com.FKFabian.medicalclinic.model.PatientCreateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DoctorValidator {


    public static void checkIfAnyDoctorsValueIsNull(DoctorCreateDto doctorCreateDto) {
        if (doctorCreateDto.getEmail() == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (doctorCreateDto.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        if (doctorCreateDto.getName() == null) {
            throw new IllegalArgumentException("First name cannot be null");
        }
        if (doctorCreateDto.getSurname() == null) {
            throw new IllegalArgumentException("Last name cannot be null");
        }
        if (doctorCreateDto.getSpecialization() == null) {
            throw new IllegalArgumentException("Specialization name cannot be null");
        }
    }
}
