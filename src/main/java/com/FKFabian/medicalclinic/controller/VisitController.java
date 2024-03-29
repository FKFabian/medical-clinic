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
    public List<VisitDto> getVisits(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                    @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return visitService.getVisits(pageNumber, pageSize);
    }

    @PostMapping("/{email}")
    public VisitDto addVisit(@RequestBody VisitCreateDto visitCreateDto, @PathVariable("email") String email) {
        return visitService.addVisit(visitCreateDto, email);
    }
}
