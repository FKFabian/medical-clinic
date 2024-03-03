package com.FKFabian.medicalclinic.mapper;

import com.FKFabian.medicalclinic.model.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitMapperTest {
    VisitMapper visitMapper = Mappers.getMapper(VisitMapper.class);

    @ParameterizedTest
    @MethodSource("dataToVisitDtoTest")
    void toVisitDtoTest(Visit visit, VisitDto expectedVisit) {
        VisitDto result = visitMapper.toVisitDto(visit);
        assertEquals(expectedVisit.getDoctorId(), result.getDoctorId());
    }

    @ParameterizedTest
    @MethodSource("dataToVisitTest")
    void toVisitTest(VisitCreateDto visitCreateDto, Visit expectedVisit) {
        Visit result = visitMapper.toVisit(createVisitCreateDto());
        assertEquals(expectedVisit.getStartingVisitTime(), result.getStartingVisitTime());
    }

    @ParameterizedTest
    @MethodSource("dataToVisitCreateDtoTest")
    void toVisitCreateDtoTest(Visit visit, VisitCreateDto expectedVisit) {
        VisitCreateDto result = visitMapper.toVisitCreateDto(visit);
        assertEquals(expectedVisit.getStartingVisitDate(), result.getStartingVisitDate());
    }

    private static Stream<Arguments> dataToVisitCreateDtoTest() {
        return Stream.of(
                Arguments.of(new Visit(), createVisitCreateDtoNull()),
                Arguments.of(createVisit(), createVisitCreateDto())
        );
    }

    private static Stream<Arguments> dataToVisitTest() {
        return Stream.of(
                Arguments.of(createVisitCreateDtoNull(), new Visit()),
                Arguments.of(createVisitCreateDto(), createVisit())
        );
    }

    private static VisitCreateDto createVisitCreateDtoNull() {
        return new VisitCreateDto(null, null);
    }

    private static VisitCreateDto createVisitCreateDto() {
        return new VisitCreateDto(LocalDateTime.of(2000, 1, 1, 1, 1),
                LocalDateTime.of(2000, 1, 1, 1, 2));
    }

    private static Stream<Arguments> dataToVisitDtoTest() {
        return Stream.of(
                Arguments.of(new Visit(), createVisitDtoNull()),
                Arguments.of(createVisit(), createVisitDto())
        );
    }

    private static VisitDto createVisitDto() {
        return new VisitDto(LocalDateTime.of(2000, 2, 2, 2, 2),
                LocalDateTime.of(2000, 2, 2, 2, 3), 1L, 11L);
    }

    private static VisitDto createVisitDtoNull() {
        return new VisitDto(null, null, null, null);
    }

    private static Visit createVisit() {
        Patient patient = new Patient(1L, "email@com", "password", "CardId1", "John", "Smith", "333-222",
                LocalDate.of(1900, 2, 2), new ArrayList<>());
        Doctor doctor = new Doctor(11L, "doc@gmail", "password", "Jan", "Nowak",
                "surgeon", new ArrayList<>(), new ArrayList<>());
        return new Visit(1L, LocalDateTime.of(2000, 1, 1, 1, 1),
                LocalDateTime.of(2000, 1, 1, 1, 2), patient, doctor);
    }
}
