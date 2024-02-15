package com.FKFabian.medicalclinic.controller;

import com.FKFabian.medicalclinic.model.FacilityCreateDto;
import com.FKFabian.medicalclinic.model.FacilityDTO;
import com.FKFabian.medicalclinic.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/facilities")
public class FacilityController {
    private final FacilityService facilityService;

    @GetMapping
    public List<FacilityDTO> getFacilities() {
        return facilityService.getFacilities();
    }

    @GetMapping("/{id}")
    public FacilityDTO getFacility(@PathVariable("id") Long id) {
        return facilityService.getFacility(id);
    }

    @PostMapping
    public FacilityDTO addFacility(@RequestBody FacilityCreateDto facilityCreateDto) {
        return facilityService.addFacility(facilityCreateDto);
    }
}
