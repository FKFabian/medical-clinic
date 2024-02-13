package com.FKFabian.medicalclinic.service;

import com.FKFabian.medicalclinic.exceptions.PatientNotFoundException;
import com.FKFabian.medicalclinic.mapper.PatientMapper;
import com.FKFabian.medicalclinic.model.Patient;
import com.FKFabian.medicalclinic.model.PatientCreateDto;
import com.FKFabian.medicalclinic.model.PatientDTO;
import com.FKFabian.medicalclinic.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PatientServiceTest {

    PatientService patientService;
    PatientRepository patientRepository;
    PatientMapper patientMapper;

    @BeforeEach
        // wykonaj mi ta metoda ktora jest oznaczona ta adnotacja przed kazdym testem
    void init() {
        this.patientRepository = Mockito.mock(PatientRepository.class); // tworzymy mocka PatientRepository i przypisujemy do pola patientRepository
        this.patientMapper = Mappers.getMapper(PatientMapper.class); // tworzymy mappera PRAWDZIWEGO i przypisujemy go do pola patientMapper
        this.patientService = new PatientService(patientRepository, patientMapper);
    }

    @Test
    void getPatients_DataCorrect_PatientDtoListReturned() {
        // given
        Patient patient1 = new Patient(1L, "patient1@gmail.com"
                , "pass1", "idCard1", "John"
                , "Smith", "444-444-444"
                , LocalDate.of(2000, 12, 5));

        Patient patient2 = new Patient(1L, "patient1@gmail.com"
                , "pass1", "idCard1", "John"
                , "Smith", "444-444-444"
                , LocalDate.of(1999, 10, 3));

        List<Patient> patients = Arrays.asList(patient1, patient2);
        when(patientRepository.findAll()).thenReturn(patients);
        // when
        var result = patientRepository.findAll();
        // then
        Assertions.assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    void getPatient_InvalidEmail_ThrowsPatientNotFoundException() {
        // given
        String email = "java@invalid.com";
        // when
        PatientNotFoundException result = assertThrows(PatientNotFoundException.class
                , () -> patientService.getPatient(email));

        // then
        assertEquals("Patient with given email " + email + " not found.", result.getMessage());
    }

    @Test
    void getPatient_CorrectEmail_ReturnPatient() {
        // given
        Patient patient = new Patient(1L, "patient1@gmail.com"
                , "pass1", "idCard1", "John"
                , "Smith", "444-444-444"
                , LocalDate.of(2000, 12, 5));

        String email = "patient1@gmail.com";
        when(patientRepository.findByEmail(email)).thenReturn(Optional.of(patient));
        //when
        var result = patientService.getPatient(email);
        // then
        assertNotNull(result);
        assertEquals("patient1@gmail.com", result.getEmail());
    }

    @Test
    void addPatient_emailIsNull_ThrowsIllegalArgumentException() {
        // given
        PatientCreateDto patientCreateDto = new PatientCreateDto(null
                , "pass1", "idCard1", "John"
                , "Smith", "333-333-333"
                , LocalDate.of(2000, 2, 2));
        //when
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class
                , () -> patientService.addPatient(patientCreateDto));

        // then
        assertEquals("Email cannot be null", result.getMessage());
    }

    @Test
    void addPatient_CorrectData_ReturnPatient() {
        //given
        PatientCreateDto patientCreateDto = new PatientCreateDto("patient@email.com"
                , "pass1", "idCard1", "John"
                , "Smith", "333-333-333"
                , LocalDate.of(2000, 2, 2));

        Patient patient = new Patient(1L, "patient1@gmail.com"
                , "pass1", "idCard1", "John"
                , "Smith", "444-444-444"
                , LocalDate.of(2000, 12, 5));

        // co robi any? Any mowi nam ze nie jest wazne jaki argument przyjdzie,
        // ma zostac zwrocony ten patient
        when(patientRepository.save(any())).thenReturn(patient);
        // when
        var result = patientService.addPatient(patientCreateDto);
        //then
        assertNotNull(result);
        assertEquals("patient@email.com", result.getEmail());

    }

    @Test
    void deletePatient_CorrectData_ReturnPatient() {
        //given
        String email = "patient1@gmail.com";
        Patient patient = new Patient(1L, "patient1@gmail.com"
                , "pass1", "idCard1", "John"
                , "Smith", "444-444-444"
                , LocalDate.of(2000, 12, 5));

        when(patientRepository.findByEmail(email)).thenReturn(Optional.of(patient));

        //when
        patientService.delete(email);

        // then
        verify(patientRepository).delete(patient);
    }

    @Test
    void delete_invalidEmail_ThrowsPatientNotFoundException() {
        // given
        String email = "wrong@email.com";
        //when
        PatientNotFoundException result = assertThrows(PatientNotFoundException.class
                , () -> patientService.delete(email));
        //then

        assertEquals("Patient with given email " + email + " not found.", result.getMessage());

    }

    @Test
    void updatePatient_invalidEmail_ThrowsPatientNotFoundException() {
        // given
        String email = "wrong@email";
        // when
        PatientNotFoundException result = assertThrows(PatientNotFoundException.class
                , () -> patientService.updatePatient(email, any())); //  czy moge zastosowac any zamiast patientCreateDto?
        //then
        assertEquals("Patient with given email " + email + " not found.", result.getMessage());
    }

    @Test
    void updatePatient_CorrectData_ReturnPatientDto() {
        // given
        String email = "patient@gmai.com";
        PatientCreateDto patientCreateDto = new PatientCreateDto("patient@gmail.com"
                , "pass123", "idCard1", "John"
                , "Smith", "333-333-333"
                , LocalDate.of(2000, 12, 5));
        Patient patient = new Patient(1L, "patient@gmail.com"
                , "pass1", "idCard1", "John"
                , "Smith", "444-444-444"
                , LocalDate.of(2000, 12, 5));

        when(patientRepository.findByEmail(email)).thenReturn(Optional.of(patient));
        when(patientRepository.save(patient)).thenReturn(patient);
        //when
        PatientDTO result = patientService.updatePatient(email, patientCreateDto);
        // then
        assertEquals("333-333-333", result.getPhoneNumber());
        assertEquals("333-333-333", patient.getPhoneNumber());
        assertEquals("pass123", patient.getPassword());
    }

    @Test
    void updatePassword_invalidEmail_ThrowsPatientNotFoundException() {
        // given
        String email = "wrong@email.com";
        // when
        PatientNotFoundException result = assertThrows(PatientNotFoundException.class
                , () -> patientService.updatePassword(email, any()));
        // then
        assertEquals("Patient with given email " + email + " not found."
                , result.getMessage());
    }

    @Test
    void updatePassword_CorrectData_ResultPatientDto() {
        //given
        String email = "patient@gmail.com";
        String newPassword = "newpassword";
        Patient patient = new Patient(1L, "patient@gmail.com"
                , "pass1", "idCard1", "John"
                , "Smith", "444-444-444"
                , LocalDate.of(2000, 12, 5));
        Patient updatePatient = new Patient(1L, "patient@gmail.com"
                , "newpassword", "idCard1", "John"
                , "Smith", "444-444-444"
                , LocalDate.of(2000, 12, 5));
        when(patientRepository.findByEmail(email)).thenReturn(Optional.of(patient));
        when(patientRepository.save(patient)).thenReturn(patient);

        // when
        PatientDTO result = patientService.updatePassword(email, newPassword);

        //then
        assertEquals("newpassword", patient.getPassword());

    }
}


