package com.FKFabian.medicalclinic.service;

import com.FKFabian.medicalclinic.common.PatientValidator;
import com.FKFabian.medicalclinic.common.VisitValidator;
import com.FKFabian.medicalclinic.mapper.VisitMapper;
import com.FKFabian.medicalclinic.model.*;
import com.FKFabian.medicalclinic.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;

    public List<VisitDto> getVisits() {
        List<Visit> visits = visitRepository.findAll();
        return visitMapper.toVisitDtoList(visits);
    }

    public VisitDto addVisit(VisitCreateDto visitCreateDto) {
        VisitValidator.checkIfAnyVisitsValueIsNull(visitCreateDto);
        VisitValidator.checkIfVisitIsAvailable(visitCreateDto, visitRepository.findAll());
        Visit visit = visitMapper.toVisit(visitCreateDto);
        Visit saveVisit = visitRepository.save(visit);
        return visitMapper.toVisitDto(visit);
    }
}
