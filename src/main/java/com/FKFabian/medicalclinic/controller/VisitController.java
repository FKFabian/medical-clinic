package com.FKFabian.medicalclinic.controller;

import com.FKFabian.medicalclinic.model.VisitCreateDto;
import com.FKFabian.medicalclinic.model.VisitDto;
import com.FKFabian.medicalclinic.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/visits")
public class VisitController {
    private final VisitService visitService;

    @GetMapping
    public List<VisitDto> getVisits() {
        return visitService.getVisits();
    }

    @PostMapping
    public void addVisit(@RequestBody VisitCreateDto visitCreateDto) {
        visitService.addVisit(visitCreateDto);
    }

}
