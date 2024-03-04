package com.FKFabian.medicalclinic.service;

import com.FKFabian.medicalclinic.exceptions.DoctorNotFoundException;
import com.FKFabian.medicalclinic.mapper.DoctorMapper;
import com.FKFabian.medicalclinic.model.Doctor;
import com.FKFabian.medicalclinic.model.DoctorCreateDto;
import com.FKFabian.medicalclinic.model.DoctorDTO;
import com.FKFabian.medicalclinic.model.Facility;
import com.FKFabian.medicalclinic.repository.DoctorRepository;
import com.FKFabian.medicalclinic.repository.FacilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DoctorServiceTest {
    DoctorService doctorService;
    DoctorMapper doctorMapper;
    DoctorRepository doctorRepository;
    FacilityRepository facilityRepository;

    @BeforeEach
    void init() {
        this.doctorRepository = Mockito.mock(DoctorRepository.class);
        this.facilityRepository = Mockito.mock(FacilityRepository.class);
        this.doctorMapper = Mappers.getMapper(DoctorMapper.class);
        this.doctorService = new DoctorService(doctorRepository, doctorMapper, facilityRepository);
    }

    @Test
    void getDoctors_dataCorrect_ReturnDoctorsList() {
        //given
        Doctor doctor1 = new Doctor(1L, "111", "111", "111"
                , "111", "111", new ArrayList<>(), new ArrayList<>());
        Doctor doctor2 = new Doctor(2L, "222", "222", "222"
                , "222", "222", new ArrayList<>(), new ArrayList<>());
        List<Doctor> list = Arrays.asList(doctor1, doctor2);
        when(doctorRepository.findAll()).thenReturn(list);
        //when
        var result = doctorService.getDoctors();
        //then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("111", result.get(0).getEmail());
        assertEquals("222", result.get(1).getName());
    }

    @Test
    void getDoctor_invalidEmail_ThrowsDoctorNotFoundException() {
        //given
        String email = "email";
        //when
        Exception result = assertThrows(DoctorNotFoundException.class
                , () -> doctorService.getDoctor(email));
        //then
        assertEquals("Doctor with given email " + email + " not found.", result.getMessage());
    }

    @Test
    void getDoctor_dataCorrect_ReturnDoctorDto() {
        //given
        String email = "email@gmail.com";
        Doctor doctor = new Doctor(1L, "111", "111", "111"
                , "111", "111", new ArrayList<>(), new ArrayList<>());
        when(doctorRepository.findByEmail(email)).thenReturn(Optional.of(doctor));
        //when
        DoctorDTO result = doctorService.getDoctor(email);
        //then
        assertNotNull(result);
        assertEquals("111", result.getName());
        assertEquals("111", result.getEmail());
        assertEquals(0, result.getFacilitiesId().size());
    }

    @Test
    void addDoctor_correctData_ReturnDoctorDto() {
        //given
        Doctor doctor = new Doctor(1L, "111", "111", "111"
                , "111", "111", new ArrayList<>(), new ArrayList<>());
        DoctorCreateDto doctorCreateDto = new DoctorCreateDto("111", "111"
                , "111", "111", "111");
        when(doctorRepository.save(any())).thenReturn(doctor);
        //when
        var result = doctorService.addDoctor(doctorCreateDto);
        //then
        assertNotNull(result);
        assertEquals("111", result.getEmail());
    }

    @Test
    void addDoctor_nameIsNull_ThrowsIllegalArgumentException() {
        //given
        DoctorCreateDto doctorCreateDto = new DoctorCreateDto("111", "111", null, "111", "111");
        //when
        Exception result = assertThrows(IllegalArgumentException.class,
                () -> doctorService.addDoctor(doctorCreateDto));
        //then
        assertEquals("First name cannot be null", result.getMessage());
    }

    @Test
    void assignToFacility_invalidData_ThrowsDoctorNotFoundException() {
        //given
        String email = "email@com";
        Long facilityId = 11L;
        //when
        Exception result = assertThrows(DoctorNotFoundException.class, () -> doctorService.assignToFacility(email, facilityId));
        //then
        assertEquals("Doctor with given email " + email + " not found.", result.getMessage());
    }
    @Test
    void assignToFacility_correctData_ReturnDoctorDto() {
        //given
        String email = "email@com";
        Long facilityId = 11L;
        Doctor doctor = new Doctor(1L, "email@com", "111", "111"
                , "111", "111", new ArrayList<>(), new ArrayList<>());
        Facility facility = new Facility(11L, "222", "222"
                , "222", "222", "222", new ArrayList<>());
        when(doctorRepository.findByEmail(email)).thenReturn(Optional.of(doctor));
        when(facilityRepository.findById(facilityId)).thenReturn(Optional.of(facility));
        when(doctorRepository.save(doctor)).thenReturn(doctor);
        //when
        DoctorDTO result = doctorService.assignToFacility(email, facilityId);
        //then
        assertNotNull(result);
        assertEquals("email@com", result.getEmail());
        assertEquals(11, result.getFacilitiesId().get(0));
        assertEquals(1, result.getFacilitiesId().size());
    }
}
