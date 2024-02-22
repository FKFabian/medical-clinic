package com.FKFabian.medicalclinic.common;

import com.FKFabian.medicalclinic.model.Visit;
import com.FKFabian.medicalclinic.model.VisitCreateDto;
import com.FKFabian.medicalclinic.repository.VisitRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VisitValidator {
    private static VisitRepository visitRepository;

    public static void checkIfVisitIsAvailable(VisitCreateDto visitCreateDto, List<Visit> visits) {
        checkIfDateIsInThePast(visitCreateDto);
        checkIfTimeIsQuarterOfAnHour(visitCreateDto);
    }

    private static void checkIfDateIsInThePast(VisitCreateDto visitCreateDto) {
        if (visitCreateDto.getStartingVisitDate().isBefore(LocalDateTime.now())) {
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
        if (visitCreateDto.getStartingVisitDate() == null) {
            throw new IllegalArgumentException("Start of date cannot be null");
        }
        if (visitCreateDto.getEndingVisitDate() == null) {
            throw new IllegalArgumentException("End of date cannot be null");
        }
    }
}
