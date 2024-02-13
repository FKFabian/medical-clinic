package com.FKFabian.medicalclinic.common;

import com.FKFabian.medicalclinic.model.DoctorCreateDto;
import com.FKFabian.medicalclinic.model.FacilityCreateDto;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static com.FKFabian.medicalclinic.common.FacilityValidator.checkIfAnyFacilitiesValueIsNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class FacilityValidatorTest {

    @ParameterizedTest
    @MethodSource("providedFacilitiesWithNull")
    void checkIfAnyFacilitiesValueIsNullTest(FacilityCreateDto facilityCreateDto, String expected) {
        Exception result = assertThrows(IllegalArgumentException.class
                , () -> checkIfAnyFacilitiesValueIsNull(facilityCreateDto));
        assertEquals(expected, result.getMessage());
    }

    private static Stream<Arguments> providedFacilitiesWithNull() {
        return Stream.of(
                Arguments.of(new FacilityCreateDto(null, "111", "111"
                        , "111", "111"), "Name cannot be null"),
                Arguments.of(new FacilityCreateDto("111", null, "111"
                        , "111", "111"), "City cannot be null"),
                Arguments.of(new FacilityCreateDto("111", "111", null
                        , "111", "111"), "Zip code cannot be null"),
                Arguments.of(new FacilityCreateDto("111", "111", "111"
                        , null, "111"), "Street cannot be null"),
                Arguments.of(new FacilityCreateDto("111", "111", "111"
                        , "111", null), "Building number cannot be null")

        );
    }
}
