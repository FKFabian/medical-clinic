package com.FKFabian.medicalclinic.common;

import com.FKFabian.medicalclinic.model.VisitCreateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VisitValidator {

    public static void checkIfVisitIsAvailable(VisitCreateDto visitCreateDto) {
        checkIfStartingDateIsInThePast(visitCreateDto);
        checkIfEndingDateIsInThePast(visitCreateDto);
        checkIfTimeIsQuarterOfAnHour(visitCreateDto);
    }

    private static void checkIfStartingDateIsInThePast(VisitCreateDto visitCreateDto) {
        if (visitCreateDto.getStartingVisitDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot create a visit for a past date.");
        }
    }

    private static void checkIfEndingDateIsInThePast(VisitCreateDto visitCreateDto) {
        if (visitCreateDto.getEndingVisitDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot create a visit for a past date.");
        }
    }

    private static void checkIfTimeIsQuarterOfAnHour(VisitCreateDto visitCreateDto) {
        int minutes = visitCreateDto.getStartingVisitDate().getMinute();
        if (minutes % 15 != 0) {
            throw new IllegalArgumentException("Visit time must be in quarter-hour intervals.");
        }
    }

    public static void checkIfAnyVisitsValueIsNull(VisitCreateDto visitCreateDto) {
        if (visitCreateDto.getStartingVisitDate() == null || visitCreateDto.getEndingVisitDate() == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
    }
}
