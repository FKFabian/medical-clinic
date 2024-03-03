package com.FKFabian.medicalclinic.mapper;

import com.FKFabian.medicalclinic.model.Doctor;
import com.FKFabian.medicalclinic.model.Facility;
import com.FKFabian.medicalclinic.model.FacilityCreateDto;
import com.FKFabian.medicalclinic.model.FacilityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FacilityMapper {
    @Mapping(target = "doctorsId", source = "doctors", qualifiedByName = "mapToDoctors")
    FacilityDTO toFacilityDto(Facility facility);

    Facility toFacility(FacilityCreateDto facilityCreateDto);

    @Named("mapToDoctors")
    default List<Long> mapDoctors(List<Doctor> doctors) {
        if (doctors == null) {
            return new ArrayList<>();
        }
        return doctors.stream()
                .map(Doctor::getId)
                .toList();
    }

    List<FacilityDTO> toPatientsDto(List<Facility> patients);
}
