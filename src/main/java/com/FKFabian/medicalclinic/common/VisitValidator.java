package com.FKFabian.medicalclinic.common;

import com.FKFabian.medicalclinic.exceptions.ObjectAlreadyExistException;
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
        checkDate(visitCreateDto);
        checkTime(visitCreateDto);
        checkIfAnyVisitAlreadyExist(visitCreateDto, visits);
    }

    private static void checkDate(VisitCreateDto visitCreateDto) {
        if (visitCreateDto.getStartingVisitDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot create a visit for a past date.");
        }
    }

    private static void checkTime(VisitCreateDto visitCreateDto) {
        int minutes = visitCreateDto.getStartingVisitDate().getMinute();
        if (minutes % 15 != 0) {
            throw new IllegalArgumentException("Visit time must be in quarter-hour intervals.");
        }
    }

    private static void checkIfAnyVisitAlreadyExist(VisitCreateDto visitCreateDto, List<Visit> visits) {
        for (Visit visit : visits) {
            if (visit.getStartingVisitTime().equals(visitCreateDto.getStartingVisitDate())) {
                throw new ObjectAlreadyExistException("Visit with given data already exist");
            }
        }
    }

    public static void checkIfDoctorHasAnyVisit(LocalDateTime startingVisitDate, LocalDateTime endingVisitDate, Long doctorId) {
        List<Visit> existingVisits = visitRepository.findAllDoctorVisits(startingVisitDate, endingVisitDate, doctorId);
        if (!existingVisits.isEmpty()) {
            throw new IllegalArgumentException("The doctor's visits is already occupied at the specified time.");
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
