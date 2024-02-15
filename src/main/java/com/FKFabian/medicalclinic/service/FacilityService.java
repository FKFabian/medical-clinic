package com.FKFabian.medicalclinic.service;

import com.FKFabian.medicalclinic.common.DoctorValidator;
import com.FKFabian.medicalclinic.common.FacilityValidator;
import com.FKFabian.medicalclinic.common.PatientValidator;
import com.FKFabian.medicalclinic.exceptions.FacilityNotFoundException;
import com.FKFabian.medicalclinic.mapper.DoctorMapper;
import com.FKFabian.medicalclinic.mapper.FacilityMapper;
import com.FKFabian.medicalclinic.model.*;
import com.FKFabian.medicalclinic.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FacilityService {
    private final FacilityRepository facilityRepository;
    private final FacilityMapper facilityMapper;
    private final DoctorMapper doctorMapper;

    public List<FacilityDTO> getFacilities() {
        List<Facility> facilities = facilityRepository.findAll();
        return facilityMapper.toPatientDtoList(facilities);
    }

    public FacilityDTO getFacility(Long id) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new FacilityNotFoundException("Facility with given email " + id + " not found."));
        return facilityMapper.toFacilityDto(facility);
    }

    public FacilityDTO addFacility(FacilityCreateDto facilityCreateDto) {
        FacilityValidator.checkIfAnyFacilityAlreadyExist(facilityCreateDto, facilityRepository.findAll());
        FacilityValidator.checkIfAnyFacilitiesValueIsNull(facilityCreateDto);
        Facility facility = facilityMapper.toFacility(facilityCreateDto);
        Facility savedFacility = facilityRepository.save(facility);
        return facilityMapper.toFacilityDto(savedFacility);
    }
}


