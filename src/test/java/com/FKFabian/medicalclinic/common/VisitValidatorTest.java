package com.FKFabian.medicalclinic.common;

import com.FKFabian.medicalclinic.model.VisitCreateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VisitValidatorTest {
    @ParameterizedTest
    @MethodSource("provideVisitsWithNull")
    void checkIfAnyVisitsValueIsNull(VisitCreateDto visitCreateDto, String expected) {
        Exception result = assertThrows(IllegalArgumentException.class
                , () -> VisitValidator.checkIfAnyVisitsValueIsNull(visitCreateDto));
        assertEquals(expected, result.getMessage());
    }

    private static Stream<Arguments> provideVisitsWithNull() {
        return Stream.of(
                Arguments.of(new VisitCreateDto(null
                                , LocalDateTime.of(2000, 1, 1, 1, 1))
                        , "Start of date cannot be null"),
                Arguments.of(new VisitCreateDto(LocalDateTime
                        .of(2000, 1, 1, 1, 1), null), "End of date cannot be null")
        );
    }

    @Test
    void checkIfVisitIsAvailable_correctData() {
        //given
        VisitCreateDto visitCreateDto = new VisitCreateDto(LocalDateTime
                .of(2025, 1, 1, 1, 0), LocalDateTime
                .of(2025, 1, 2, 3, 15));
        VisitValidator.checkIfVisitIsAvailable(visitCreateDto);
    }

    @Test
    void checkIfVisitIsAvailable_StartingDateIsInThePast_ThrowsIllegalArgumentException() {
        //given
        VisitCreateDto visitCreateDto = new VisitCreateDto(LocalDateTime.now().minusDays(1), LocalDateTime.now());
        //when
        Exception result = assertThrows(IllegalArgumentException.class, () ->
                VisitValidator.checkIfVisitIsAvailable(visitCreateDto));
        //then
        assertEquals("Cannot create a visit for a past date.", result.getMessage());
    }

    @Test
    void checkIfVisitIsAvailable_StartingTimeIsNotInQuarterHourIntervals_ThrowsIllegalArgumentException() {
        //given
        LocalDateTime startingTime = LocalDateTime
                .of(2025, 2, 2, 2, 2);
        VisitCreateDto visitCreateDto = new VisitCreateDto(startingTime, LocalDateTime.of(2025, 2, 2, 23, 2));
        //when
        Exception result = assertThrows(IllegalArgumentException.class, () ->
                VisitValidator.checkIfVisitIsAvailable(visitCreateDto));
        //then
        assertEquals("Visit time must be in quarter-hour intervals.",
                result.getMessage());
    }
}
