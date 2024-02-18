package com.FKFabian.medicalclinic.mapper;

import com.FKFabian.medicalclinic.model.Doctor;
import com.FKFabian.medicalclinic.model.DoctorCreateDto;
import com.FKFabian.medicalclinic.model.DoctorDTO;
import com.FKFabian.medicalclinic.model.Facility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    @Mapping(target = "facilitiesId", source = "facilities", qualifiedByName = "mapToFacility")
    DoctorDTO toDoctorDto(Doctor doctor);

    Doctor toDoctor(DoctorCreateDto doctorCreateDto);

    @Named("mapToFacility")
    default List<Long> mapFacilities(List<Facility> facilities) {
        if (facilities == null) {
            return new ArrayList<>();
        }
        return facilities.stream()
                .map(Facility::getId)
                .toList();
    }

    List<DoctorDTO> toDoctorDtoList(List<Doctor> doctors);
}
