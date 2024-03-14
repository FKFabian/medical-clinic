package com.FKFabian.medicalclinic.remote.medicalclient;

import com.FKFabian.medicalclinic.model.PatientCreateDto;
import com.FKFabian.medicalclinic.model.PatientDTO;
import com.FKFabian.medicalclinic.model.VisitDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "medicalClient", url = "localhost:8080", configuration = MyErrorDecoder.class)
public interface MedicalClient {

    @GetMapping("/patients")
    List<PatientDTO> getPatients();

    @GetMapping("/patients/{email}")
    PatientDTO getPatient(@PathVariable("email") String email);

    @GetMapping("/patients/{email}/visits")
    List<VisitDto> getPatientVisits(@PathVariable("email") String email);

    @DeleteMapping("/patients/{email}")
    void delete(@PathVariable("email") String email);

    @PostMapping("/patients")
    PatientDTO addPatient(@RequestBody PatientCreateDto patientCreateDto);

    @PutMapping("/patients/{email}")
    PatientDTO updatePatient(@PathVariable("email") String email);
}
