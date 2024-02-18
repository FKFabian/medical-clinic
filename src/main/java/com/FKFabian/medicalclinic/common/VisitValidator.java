package com.FKFabian.medicalclinic.common;

import com.FKFabian.medicalclinic.exceptions.ObjectAlreadyExistException;
import com.FKFabian.medicalclinic.model.Visit;
import com.FKFabian.medicalclinic.model.VisitCreateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VisitValidator {
    public static void checkIfVisitIsAvailable(VisitCreateDto visitCreateDto, List<Visit> visits) {
        checkDate(visitCreateDto);
        checkTime(visitCreateDto);
        checkIfAnyVisitAlreadyExist(visitCreateDto, visits);
    }

    private static void checkDate(VisitCreateDto visitCreateDto) {
        if (visitCreateDto.getVisitDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot create a visit for a past date.");
        }
    }

    private static void checkTime(VisitCreateDto visitCreateDto) {
        int second = visitCreateDto.getVisitDate().toLocalTime().toSecondOfDay();
        if (second % (15 * 60) != 0) {
            throw new IllegalArgumentException("Visit time must be in quarter-hour intervals.");
        }
    }

    public static void checkIfAnyVisitAlreadyExist(VisitCreateDto visitCreateDto, List<Visit> visits) {
        for (Visit visit : visits) {
            if (visit.getVisitDate().equals(visitCreateDto.getVisitDate())) {
                throw new ObjectAlreadyExistException("Visit with given data already exist");
            }
        }
    }

    public static void checkIfAnyVisitsValueIsNull(VisitCreateDto visitCreateDto) {
        if (visitCreateDto.getVisitDate() == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (visitCreateDto.getPatient() == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        }
    }
}
