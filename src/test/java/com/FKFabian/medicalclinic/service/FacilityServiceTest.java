package com.FKFabian.medicalclinic.service;

import com.FKFabian.medicalclinic.exceptions.FacilityNotFoundException;
import com.FKFabian.medicalclinic.mapper.DoctorMapper;
import com.FKFabian.medicalclinic.mapper.FacilityMapper;
import com.FKFabian.medicalclinic.model.Facility;
import com.FKFabian.medicalclinic.model.FacilityCreateDto;
import com.FKFabian.medicalclinic.model.FacilityDTO;
import com.FKFabian.medicalclinic.repository.FacilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FacilityServiceTest {
    FacilityService facilityService;
    FacilityMapper facilityMapper;
    FacilityRepository facilityRepository;
    DoctorMapper doctorMapper;

    @BeforeEach
    void init() {
        this.facilityRepository = Mockito.mock(FacilityRepository.class);
        this.facilityMapper = Mappers.getMapper(FacilityMapper.class);
        this.doctorMapper = Mappers.getMapper(DoctorMapper.class);
        this.facilityService = new FacilityService(facilityRepository, facilityMapper, doctorMapper);
    }

    @Test
    void getFacilities_correctData_ReturnFacilityDto() {
        //given
        Facility facility1 = new Facility(1L, "111", "111", "111",
                "111", "111", List.of());
        Facility facility2 = new Facility(2L, "222", "222", "222",
                "222", "222", List.of());
        List<Facility> facilities = Arrays.asList(facility1, facility2);
        when(facilityRepository.findAll()).thenReturn(facilities);
        //when
        var result = facilityService.getFacilities();
        //then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getFacility_correctData_ReturnFacilityDto() {
        //given
        Long facilityId = 1L;
        Facility facility = new Facility(1L, "111", "111", "111",
                "111", "111", List.of());
        when(facilityRepository.findById(facilityId)).thenReturn(Optional.of(facility));
        //when
        var result = facilityService.getFacility(facilityId);
        //then
        assertNotNull(result);
        assertEquals("111", result.getName());
    }

    @Test
    void getFacility_invalidId_ThrowsFacilityNotFoundException() {
        //given
        Long id = 123L;
        //when
        Exception result = assertThrows(FacilityNotFoundException.class
                , () -> facilityService.getFacility(id));
        //then
        assertEquals("Facility with given email " + id + " not found.", result.getMessage());
    }

    @Test
    void addFacility_cityIsNull_ThrowsIllegalArgumentException() {
        //given
        FacilityCreateDto facilityCreateDto = new FacilityCreateDto("111", null
                , "111", "111", "111");
        //when
        Exception result = assertThrows(IllegalArgumentException.class
                , () -> facilityService.addFacility(facilityCreateDto));
        //then
        assertEquals("City cannot be null", result.getMessage());
    }

    @Test
    void addFacility_correctData_ReturnFacilityDto() {
        //given
        Facility facility = new Facility(1L, "111", "111", "111",
                "111", "111", List.of());
        FacilityCreateDto facilityCreateDto = new FacilityCreateDto("222", "222"
                , "222", "222", "222");
        when(facilityRepository.save(facility)).thenReturn(facility);
        //when
        var result = facilityService.addFacility(facilityCreateDto);
        //then
        assertNotNull(result);
        assertEquals("222", result.getName());
    }
}
