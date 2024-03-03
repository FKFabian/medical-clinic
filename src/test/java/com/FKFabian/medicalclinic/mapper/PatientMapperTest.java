package com.FKFabian.medicalclinic.mapper;

import com.FKFabian.medicalclinic.model.Patient;
import com.FKFabian.medicalclinic.model.PatientCreateDto;
import com.FKFabian.medicalclinic.model.PatientDTO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientMapperTest {
    PatientMapper patientMapper = Mappers.getMapper(PatientMapper.class);

    @ParameterizedTest
    @MethodSource("dataForPatientDtoTest")
    void toPatientDtoTest(Patient patient, PatientDTO expectedPatient) {
        PatientDTO result = patientMapper.toPatientDto(patient);
        assertEquals(expectedPatient, result);
    }

    private static Stream<Arguments> dataForPatientDtoTest() {
        return Stream.of(
                Arguments.of(createPatient(), createPatientDto()),
                Arguments.of(new Patient(), createPatientDtoNull())
        );
    }

    @ParameterizedTest
    @MethodSource("dataForPatientTest")
    void toPatientTest(PatientCreateDto patientCreateDto, Patient expectedPatient) {
        Patient result = patientMapper.toPatient(patientCreateDto);
        assertEquals(expectedPatient.getFirstName(), result.getFirstName());
        assertEquals(expectedPatient.getPassword(), result.getPassword());
        assertEquals(expectedPatient.getId(), result.getId());
    }

    private static Stream<Arguments> dataForPatientTest() {
        return Stream.of(
                Arguments.of(createPatientCreateDto(), createPatient()),
                Arguments.of(createPatientCreateDtoNull(), new Patient())
        );
    }

    private static Patient createPatient() {
        return new Patient(null, "patient1@gmail.com"
                , "pass1", "idCard1", "John"
                , "Smith", "333-333-333"
                , LocalDate.of(2000, 12, 5), new ArrayList<>());
    }

    private static PatientDTO createPatientDto() {
        return new PatientDTO("patient1@gmail.com"
                , "idCard1", "John", "Smith"
                , "333-333-333", LocalDate.of(2000, 12, 5));
    }

    private static PatientDTO createPatientDtoNull() {
        return new PatientDTO(null
                , null, null, null
                , null, null);
    }

    private static PatientCreateDto createPatientCreateDto() {
        return new PatientCreateDto("patient1@gmail.com"
                , "pass1", "idCard1", "John"
                , "Smith", "333-333-333", LocalDate.of(2000, 12, 5));

    }

    private static PatientCreateDto createPatientCreateDtoNull() {
        return new PatientCreateDto(null
                , null, null, null
                , null, null, null);

    }
}

