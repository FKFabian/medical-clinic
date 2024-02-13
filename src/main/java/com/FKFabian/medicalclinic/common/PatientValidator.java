package com.FKFabian.medicalclinic.common;


import com.FKFabian.medicalclinic.model.Patient;
import com.FKFabian.medicalclinic.model.PatientCreateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PatientValidator {


    public static void validatePatientDataToChange(Patient currentPatient, PatientCreateDto newDataPatient) {
        if (!(currentPatient.getIdCardNo().equals(newDataPatient.getIdCardNo()))) {
            throw new IllegalArgumentException(("You cannot change ID card number"));
        }
        checkIfAnyValueIsNull(newDataPatient);
    }

    public static void checkIfAnyValueIsNull(PatientCreateDto patientCreateDto) {
        if (patientCreateDto.getEmail() == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (patientCreateDto.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        if (patientCreateDto.getIdCardNo() == null) {
            throw new IllegalArgumentException("IDCardNo cannot be null");
        }
        if (patientCreateDto.getFirstName() == null) {
            throw new IllegalArgumentException("First name cannot be null");
        }
        if (patientCreateDto.getLastName() == null) {
            throw new IllegalArgumentException("Last name cannot be null");
        }
        if (patientCreateDto.getPhoneNumber() == null) {
            throw new IllegalArgumentException("Phone number cannot be null");
        }
        if (patientCreateDto.getBirthday() == null) {
            throw new IllegalArgumentException("Birthday cannot be null");
        }
    }

}
