package com.FKFabian.medicalclinic.mapper;

import com.FKFabian.medicalclinic.model.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDTO toPatientDto(Patient patient);

    Patient toPatient(PatientCreateDto patientCreateDto);

    List<PatientDTO> toPatientDtoList(List<Patient> patients);
}
