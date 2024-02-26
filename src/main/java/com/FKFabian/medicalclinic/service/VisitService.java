package com.FKFabian.medicalclinic.service;

import com.FKFabian.medicalclinic.common.VisitValidator;
import com.FKFabian.medicalclinic.exceptions.DoctorNotFoundException;
import com.FKFabian.medicalclinic.mapper.VisitMapper;
import com.FKFabian.medicalclinic.model.Doctor;
import com.FKFabian.medicalclinic.model.Visit;
import com.FKFabian.medicalclinic.model.VisitCreateDto;
import com.FKFabian.medicalclinic.model.VisitDto;
import com.FKFabian.medicalclinic.repository.DoctorRepository;
import com.FKFabian.medicalclinic.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;
    private final DoctorRepository doctorRepository;

    public List<VisitDto> getVisits(int pageNumber, int pageSize) {
        PageRequest sortedByStartingDateDescending = PageRequest.of(pageNumber, pageSize, Sort.by("startingVisitDate").descending());
        Page<Visit> visits = visitRepository.findAll(sortedByStartingDateDescending);
        List<Visit> visitsPageToList = visits.getContent();
        return visitMapper.toVisitsDto(visitsPageToList);

    }

    public VisitDto addVisit(VisitCreateDto visitCreateDto, String email) {
        VisitValidator.checkIfAnyVisitsValueIsNull(visitCreateDto);
        VisitValidator.checkIfVisitIsAvailable(visitCreateDto);
        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with given email " + email + " not found"));
        checkIfDoctorHasAnyVisit(
                visitCreateDto.getStartingVisitDate(), visitCreateDto.getEndingVisitDate(), doctor.getId());
        Visit visit = visitMapper.toVisit(visitCreateDto);
        visit.setDoctor(doctor);
        visitRepository.save(visit);
        return visitMapper.toVisitDto(visit);
    }

    private void checkIfDoctorHasAnyVisit(LocalDateTime startingVisitDate, LocalDateTime endingVisitDate, Long doctorId) {
        List<Visit> existingVisits = visitRepository.findAllDoctorsVisitsWithGivenTimeRange(startingVisitDate, endingVisitDate, doctorId);
        if (!existingVisits.isEmpty()) {
            throw new IllegalArgumentException("The doctor's visits is already occupied at the specified time.");
        }
    }
}
