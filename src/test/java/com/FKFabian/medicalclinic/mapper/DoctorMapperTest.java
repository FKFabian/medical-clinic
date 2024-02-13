package com.FKFabian.medicalclinic.mapper;

import com.FKFabian.medicalclinic.model.Doctor;
import com.FKFabian.medicalclinic.model.DoctorCreateDto;
import com.FKFabian.medicalclinic.model.DoctorDTO;
import com.FKFabian.medicalclinic.model.Facility;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoctorMapperTest {
    DoctorMapper doctorMapper = Mappers.getMapper(DoctorMapper.class);

    @ParameterizedTest
    @MethodSource("dataToDoctorDtoTest")
    void toDoctorDtoTest(Doctor doctor, DoctorDTO expectedDoctor) {
        DoctorDTO result = doctorMapper.toDoctorDto(doctor);
        assertEquals(expectedDoctor, result);
    } // failed - Expected :DoctorDTO(email=null, name=null, surname=null, specialization=null, facilitiesId=null)
//                Actual   :DoctorDTO(email=null, name=null, surname=null, specialization=null, facilitiesId=[])

    private static Stream<Arguments> dataToDoctorDtoTest() {
        return Stream.of(
                Arguments.of(createDoctor(), createDoctorDto()),
                Arguments.of(new Doctor(), createDoctorDtoNull())
        );
    }

    @ParameterizedTest
    @MethodSource("dataToDoctorTest")
    void toDoctorTest(DoctorCreateDto doctorCreateDto, Doctor expectedDoctor) {
        Doctor result = doctorMapper.toDoctor(doctorCreateDto);
        assertEquals(expectedDoctor.getEmail(), result.getEmail());
    } // failed

    private static Stream<Arguments> dataToDoctorTest() {
        return Stream.of(
                Arguments.of(createDoctorCreateDto(), createDoctor()),
                Arguments.of(createDoctorCreateDtoNull(), new Doctor())
        );
    }

    private static DoctorCreateDto createDoctorCreateDto() {
        return new DoctorCreateDto("111", "111"
                , "111", "111", "111");
    }

    private static DoctorCreateDto createDoctorCreateDtoNull() {
        return new DoctorCreateDto(null, null
                , null, null, null);
    }

    private static Doctor createDoctor() {
        return new Doctor(null, "111"
                , "111", "111"
                , "111", "111", new ArrayList<>());
    }

    private static DoctorDTO createDoctorDto() {
        return new DoctorDTO("111", "111"
                , "111", "111", new ArrayList<>());
    }

    private static DoctorDTO createDoctorDtoNull() {
        return new DoctorDTO(null, null
                , null, null, new ArrayList<>());
    }


}
