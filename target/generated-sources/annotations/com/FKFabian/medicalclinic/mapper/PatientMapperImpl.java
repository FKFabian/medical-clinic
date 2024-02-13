package com.FKFabian.medicalclinic.mapper;

import com.FKFabian.medicalclinic.model.Patient;
import com.FKFabian.medicalclinic.model.PatientCreateDto;
import com.FKFabian.medicalclinic.model.PatientDTO;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-13T08:53:38+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientDTO toPatientDto(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        String email = null;
        String idCardNo = null;
        String firstName = null;
        String lastName = null;
        String phoneNumber = null;
        LocalDate birthday = null;

        email = patient.getEmail();
        idCardNo = patient.getIdCardNo();
        firstName = patient.getFirstName();
        lastName = patient.getLastName();
        phoneNumber = patient.getPhoneNumber();
        birthday = patient.getBirthday();

        PatientDTO patientDTO = new PatientDTO( email, idCardNo, firstName, lastName, phoneNumber, birthday );

        return patientDTO;
    }

    @Override
    public Patient toPatient(PatientCreateDto patientCreateDto) {
        if ( patientCreateDto == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setEmail( patientCreateDto.getEmail() );
        patient.setPassword( patientCreateDto.getPassword() );
        patient.setIdCardNo( patientCreateDto.getIdCardNo() );
        patient.setFirstName( patientCreateDto.getFirstName() );
        patient.setLastName( patientCreateDto.getLastName() );
        patient.setPhoneNumber( patientCreateDto.getPhoneNumber() );
        patient.setBirthday( patientCreateDto.getBirthday() );

        return patient;
    }
}
