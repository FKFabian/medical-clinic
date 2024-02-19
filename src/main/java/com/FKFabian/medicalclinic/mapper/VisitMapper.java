package com.FKFabian.medicalclinic.mapper;

import com.FKFabian.medicalclinic.model.Visit;
import com.FKFabian.medicalclinic.model.VisitCreateDto;
import com.FKFabian.medicalclinic.model.VisitDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VisitMapper {
    VisitDto toVisitDto(Visit visit);

    Visit toVisit(VisitCreateDto visitCreateDto);

    VisitCreateDto toVisitCreateDto(Visit visit);

    List<VisitDto> toVisitDtoList(List<Visit> visits);
}
