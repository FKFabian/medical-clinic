package com.FKFabian.medicalclinic.mapper;

import com.FKFabian.medicalclinic.model.Visit;
import com.FKFabian.medicalclinic.model.VisitCreateDto;
import com.FKFabian.medicalclinic.model.VisitDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VisitMapper {
    @Mapping(target = "doctorId", source = "doctor.id")
    @Mapping(target = "patientId", source = "patient.id")
    VisitDto toVisitDto(Visit visit);

    @Mapping(source = "startingVisitDate", target = "startingVisitTime")
    @Mapping(source = "endingVisitDate", target = "endingVisitTime")
    Visit toVisit(VisitCreateDto visitCreateDto);

    VisitCreateDto toVisitCreateDto(Visit visit);

    List<VisitDto> toVisitsDto(List<Visit> visits);
}
