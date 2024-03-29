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
    @Mapping(source = "startingVisitTime", target = "startingVisitDate")
    @Mapping(source = "endingVisitTime", target = "endingVisitDate")
    VisitDto toVisitDto(Visit visit);

    @Mapping(source = "startingVisitDate", target = "startingVisitTime")
    @Mapping(source = "endingVisitDate", target = "endingVisitTime")
    Visit toVisit(VisitCreateDto visitCreateDto);

    @Mapping(source = "startingVisitTime", target = "startingVisitDate")
    @Mapping(source = "endingVisitTime", target = "endingVisitDate")
    VisitCreateDto toVisitCreateDto(Visit visit);

    List<VisitDto> toVisitsDto(List<Visit> visits);
}
