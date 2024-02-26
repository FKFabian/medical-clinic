package com.FKFabian.medicalclinic.controller;

import com.FKFabian.medicalclinic.model.PatientCreateDto;
import com.FKFabian.medicalclinic.model.PatientDTO;
import com.FKFabian.medicalclinic.model.VisitDto;
import com.FKFabian.medicalclinic.service.PatientService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public List<PatientDTO> getPatients() {
        return patientService.getPatients();
    }

    @GetMapping("/{email}")
    public PatientDTO getPatient(@PathVariable("email") @NotBlank(message = "Invalid email: Empty email") String email) {
        return patientService.getPatient(email);
    }

    @GetMapping("/{email}/visits")
    public List<VisitDto> getPatientVisits(@PathVariable("email") @NotBlank(message = "Invalid email: Empty email") String email) {
        return patientService.getVisits(email);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@NotBlank(message = "Invalid email: Empty email") @PathVariable("email") String email) {
        patientService.delete(email);
    }

    @PostMapping
    public PatientDTO addPatient(@RequestBody PatientCreateDto patientCreateDto) {
        return patientService.addPatient(patientCreateDto);
    }

    @PutMapping("/{email}")
    public PatientDTO updatePatient(@PathVariable("email") @NotBlank(message = "Invalid email: Empty email") String email, @RequestBody PatientCreateDto patientCreateDto) {
        return patientService.updatePatient(email, patientCreateDto);
    }

    @PatchMapping("/{email}")
    public PatientDTO updatePassword(@PathVariable("email") @NotBlank(message = "Invalid email: Empty email") String email, @NotBlank(message = "Invalid password: Empty password") @RequestBody String newPassword) {
        return patientService.updatePassword(email, newPassword);
    }

    @PatchMapping("/{email}/visits/{visitId}")
    public PatientDTO assignToVisit(@PathVariable("email") @NotBlank(message = "Invalid email: Empty email") String email, @PathVariable Long visitId) {
        return patientService.assignToVisit(email, visitId);
    }
}
