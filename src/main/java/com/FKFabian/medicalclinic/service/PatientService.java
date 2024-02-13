package com.FKFabian.medicalclinic.service;

import com.FKFabian.medicalclinic.common.PatientValidator;
import com.FKFabian.medicalclinic.exceptions.PatientNotFoundException;
import com.FKFabian.medicalclinic.mapper.PatientMapper;
import com.FKFabian.medicalclinic.model.Patient;
import com.FKFabian.medicalclinic.model.PatientCreateDto;
import com.FKFabian.medicalclinic.model.PatientDTO;
import com.FKFabian.medicalclinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;


    public List<PatientDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patientMapper::toPatientDto)
                .toList();
    }

    public void delete(String email) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException("Patient with given email " + email + " not found."));
        patientRepository.delete(patient);
    }


    public PatientDTO getPatient(String email) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException("Patient with given email " + email + " not found."));
        return patientMapper.toPatientDto(patient);
    }

    public PatientDTO addPatient(PatientCreateDto patientCreateDto) {
        PatientValidator.checkIfAnyValueIsNull(patientCreateDto);
        Patient patient = patientMapper.toPatient(patientCreateDto);
        patientRepository.save(patient);
        return patientMapper.toPatientDto(patient);
    }

    public PatientDTO updatePatient(String email, PatientCreateDto patientCreateDto) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException("Patient with given email " + email + " not found."));
        PatientValidator.validatePatientDataToChange(patient, patientCreateDto);
        patient.update(patientCreateDto);
        Patient updatePatient = patientRepository.save(patient);
        return patientMapper.toPatientDto(updatePatient);
    }

    public PatientDTO updatePassword(String email, String newPassword) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException("Patient with given email " + email + " not found."));
//        if (newPassword == null) {
//            throw new IllegalArgumentException("Password cannot be null");
//        } <-- to niepotrzebne jezeli w endpontcie, w Controlerze dodam adnotacje @Not Blank.
        patient.setPassword(newPassword);
        Patient updatedPatient = patientRepository.save(patient);
//        Patient patient1 = patientRepository.get(email)  <-- jezeli typ zwracay bylby void
//                .orElseThrow(() -> new NotFoundPatientException("Patient with given email " + email + " not found."));
        return patientMapper.toPatientDto(updatedPatient);
    }

}

