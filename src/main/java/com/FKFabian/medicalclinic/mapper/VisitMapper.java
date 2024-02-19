package com.FKFabian.medicalclinic.mapper;

import com.FKFabian.medicalclinic.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VisitMapper {
    @Mapping(target = "doctorId", source = "doctor", qualifiedByName = "mapDoctor")
    @Mapping(target = "patientId", source = "patient", qualifiedByName = "mapPatient")
    VisitDto toVisitDto(Visit visit);

    Visit toVisit(VisitCreateDto visitCreateDto);

    VisitCreateDto toVisitCreateDto(Visit visit);

    List<VisitDto> toVisitDtoList(List<Visit> visits);

    @Named("mapDoctor")
    default Long mapDoctorId(Doctor doctor) {
        if (doctor == null) {
            return null;
        }
        return doctor.getId();
    }

    @Named("mapPatient")
    default Long mapPatientId(Patient patient) {
        if (patient == null) {
            return null;
        }
        return patient.getId();
    }
}
