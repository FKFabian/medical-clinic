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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public List<PatientDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patientMapper.toPatientDtoList(patients);
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

    @Transactional
    public PatientDTO addPatient(PatientCreateDto patientCreateDto) {
        PatientValidator.checkIfAnyPatientAlreadyExist(patientCreateDto, patientRepository.findAll());
        PatientValidator.checkIfAnyValueIsNull(patientCreateDto);
        Patient patient = patientMapper.toPatient(patientCreateDto);
        patientRepository.save(patient);
        return patientMapper.toPatientDto(patient);
    }

    @Transactional
    public PatientDTO updatePatient(String email, PatientCreateDto patientCreateDto) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException("Patient with given email " + email + " not found."));
        PatientValidator.validatePatientDataToChange(patient, patientCreateDto);
        patient.update(patientCreateDto);
        Patient updatePatient = patientRepository.save(patient);
        return patientMapper.toPatientDto(updatePatient);
    }

    @Transactional
    public PatientDTO updatePassword(String email, String newPassword) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException("Patient with given email " + email + " not found."));
        patient.setPassword(newPassword);
        Patient updatedPatient = patientRepository.save(patient);
        return patientMapper.toPatientDto(updatedPatient);
    }
}

