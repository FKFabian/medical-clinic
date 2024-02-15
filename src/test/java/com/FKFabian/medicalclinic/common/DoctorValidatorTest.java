package com.FKFabian.medicalclinic.common;

import com.FKFabian.medicalclinic.model.DoctorCreateDto;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static com.FKFabian.medicalclinic.common.DoctorValidator.checkIfAnyDoctorsValueIsNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DoctorValidatorTest {
    @ParameterizedTest
    @MethodSource("provideDoctorsWithNull")
    void checkIfAnyDoctorsValueIsNullTest(DoctorCreateDto doctorCreateDto, String expected) {
        Exception result = assertThrows(IllegalArgumentException.class
                , () -> checkIfAnyDoctorsValueIsNull(doctorCreateDto));
        assertEquals(expected, result.getMessage());
    }

    private static Stream<Arguments> provideDoctorsWithNull() {
        return Stream.of(
                Arguments.of(new DoctorCreateDto(null, "111"
                                , "111", "111", "111")
                        , "Email cannot be null"),
                Arguments.of(new DoctorCreateDto("111", null
                                , "111", "111", "111")
                        , "Password cannot be null"),
                Arguments.of(new DoctorCreateDto("111", "111"
                                , null, "111", "111")
                        , "First cannot be null"),
                Arguments.of(new DoctorCreateDto("111", "111"
                                , "111", null, "111")
                        , "Last name cannot be null"),
                Arguments.of(new DoctorCreateDto("111", "111"
                                , "111", "111", null)
                        , "Specialization name cannot be null")
        );
    }

}
