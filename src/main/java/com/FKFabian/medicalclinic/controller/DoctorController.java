package com.FKFabian.medicalclinic.controller;

import com.FKFabian.medicalclinic.model.*;
import com.FKFabian.medicalclinic.service.DoctorService;
import com.FKFabian.medicalclinic.service.FacilityService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private final FacilityService facilityService;

    @GetMapping
    public List<DoctorDTO> getDoctors() {
        return doctorService.getDoctors();
    }


    @GetMapping("/{email}")
    public DoctorDTO getDoctor(@PathVariable("email") String email) {
        return doctorService.getDoctor(email);
    }

    @PostMapping
    public DoctorDTO addDoctor(@RequestBody DoctorCreateDto doctorCreateDto) {
        return doctorService.addDoctor(doctorCreateDto);
    }

    @PutMapping("/{email}/facilities/{facilityId}")
    public DoctorDTO assignToFacility(@PathVariable("email") @NotBlank(message = "Invalid email: Empty email") String email, @PathVariable Long facilityId) {
        return doctorService.assignToFacility(email, facilityId);
    }

}
