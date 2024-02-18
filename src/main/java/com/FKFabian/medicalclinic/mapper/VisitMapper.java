package com.FKFabian.medicalclinic.mapper;

import com.FKFabian.medicalclinic.model.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VisitMapper {
    VisitDto toVisitDto(Visit visit);

    Visit toVisit(VisitCreateDto visitCreateDto);

    List<VisitDto> toVisitDtoList(List<Visit> visits);
}
