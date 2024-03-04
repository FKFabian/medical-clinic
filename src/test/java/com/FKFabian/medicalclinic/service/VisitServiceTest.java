package com.FKFabian.medicalclinic.service;

import com.FKFabian.medicalclinic.exceptions.DoctorNotFoundException;
import com.FKFabian.medicalclinic.mapper.VisitMapper;
import com.FKFabian.medicalclinic.model.*;
import com.FKFabian.medicalclinic.repository.DoctorRepository;
import com.FKFabian.medicalclinic.repository.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class VisitServiceTest {
    VisitService visitService;
    VisitMapper visitMapper;
    VisitRepository visitRepository;
    DoctorRepository doctorRepository;

    @BeforeEach
    void init() {
        this.visitRepository = Mockito.mock(VisitRepository.class);
        this.doctorRepository = Mockito.mock(DoctorRepository.class);
        this.visitMapper = Mappers.getMapper(VisitMapper.class);
        this.visitService = new VisitService(visitRepository, visitMapper, doctorRepository);
    }

    @Test
    void getVisits_dataCorrect_ReturnVisits() {
        //given
        int pageNumber = 0;
        int pageSize = 5;
        Patient patient = new Patient(1L, "email", "password", "IdCard",
                "John", "Smith", "444-444",
                LocalDate.now(), new ArrayList<>());
        Doctor doctor = new Doctor(1L, "email", "password", "Jan", "Nowak",
                "surgeon", new ArrayList<>(), new ArrayList<>());
        Visit visit1 = new Visit(1L, LocalDateTime.of(2024, 2, 2, 2, 2),
                LocalDateTime.of(2024, 2, 2, 2, 3), patient, doctor);

        Visit visit2 = new Visit(1L, LocalDateTime.of(2023, 2, 2, 2, 2),
                LocalDateTime.of(2023, 2, 2, 2, 3), patient, doctor);
        List<Visit> visits = Arrays.asList(visit1, visit2);
        PageRequest sortedByStartingDateDescending = PageRequest.of(pageNumber, pageSize,
                Sort.by("startingVisitDate").descending());
        PageImpl<Visit> visitsPage = new PageImpl<>(visits, sortedByStartingDateDescending, 0);
        when(visitRepository.findAll(sortedByStartingDateDescending)).thenReturn(visitsPage);
        //when
        var result = visitService.getVisits(pageNumber, pageSize);
        //then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void addVisit_VisitValueAsNull_ThrowsIllegalArgumentException() {
        //given
        String email = "invalidEmail";
        VisitCreateDto visitCreateDto = new VisitCreateDto(null, LocalDateTime.now());
        //when
        Exception result = assertThrows(IllegalArgumentException.class,
                () -> visitService.addVisit(visitCreateDto, email));
        //then
        assertEquals("Date cannot be null", result.getMessage());
    }

    @Test
    void addVisit_visitDateIsPast_ThrowsIllegalArgumentException() {
        //given
        String email = "invalidEmail";
        VisitCreateDto visitCreateDto = new VisitCreateDto(LocalDateTime
                .of(1900, 1, 1, 1, 1), LocalDateTime.now());
        //when
        Exception result = assertThrows(IllegalArgumentException.class,
                () -> visitService.addVisit(visitCreateDto, email));
        //then
        assertEquals("Cannot create a visit for a past date.", result.getMessage());
    }

    @Test
    void addVisit_visitTimeIsNotQuarterOfHour_ThrowsIllegalArgumentException() {
        //given
        String email = "invalidEmail";
        VisitCreateDto visitCreateDto = new VisitCreateDto(LocalDateTime
                .of(2025, 1, 1, 1, 1), LocalDateTime
                .of(2025, 1, 2, 3, 4));
        //when
        Exception result = assertThrows(IllegalArgumentException.class,
                () -> visitService.addVisit(visitCreateDto, email));
        //then
        assertEquals("Visit time must be in quarter-hour intervals.", result.getMessage());
    }

    @Test
    void addVisit_correctData_ReturnVisit() {
        //given
        String email = "correctEmail";
        VisitCreateDto visitCreateDto = new VisitCreateDto(LocalDateTime
                .of(2025, 3, 2, 2, 0), LocalDateTime
                .of(2025, 3, 2, 2, 15));
        Doctor doctor = new Doctor(1L, "email@com", "111", "111"
                , "111", "111", new ArrayList<>(), new ArrayList<>());
        Patient patient = new Patient(1L, "email", "password", "IdCard",
                "John", "Smith", "444-444",
                LocalDate.now(), new ArrayList<>());
        Visit visit = new Visit(1L, LocalDateTime.of(2025, 3, 2, 2, 0),
                LocalDateTime.of(2025, 3, 2, 2, 15), patient, doctor);
        when(doctorRepository.findByEmail(email)).thenReturn(Optional.of(doctor));
        when(visitRepository.save(visit)).thenReturn(visit);
        //when
        VisitDto result = visitService.addVisit(visitCreateDto, email);
        //then
        assertNotNull(result);
        assertEquals(LocalDateTime
                .of(2025, 3, 2, 2, 0), result.getStartingVisitDate());
        assertEquals(1, result.getDoctorId());
    }
}