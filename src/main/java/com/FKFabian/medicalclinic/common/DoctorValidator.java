package com.FKFabian.medicalclinic.common;

import com.FKFabian.medicalclinic.exceptions.ObjectAlreadyExistException;
import com.FKFabian.medicalclinic.model.Doctor;
import com.FKFabian.medicalclinic.model.DoctorCreateDto;
import com.FKFabian.medicalclinic.model.Patient;
import com.FKFabian.medicalclinic.model.PatientCreateDto;
import com.FKFabian.medicalclinic.repository.DoctorRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

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

    public static void checkIfAnyDoctorAlreadyExist(DoctorCreateDto doctorCreateDto, List<Doctor> doctors) {
        for (Doctor doctor : doctors) {
            if (doctor.getEmail().equals(doctorCreateDto.getEmail())) {
                throw new ObjectAlreadyExistException("Doctor with given email already exist");
            }
        }
    }
}
