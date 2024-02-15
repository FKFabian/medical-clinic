package com.FKFabian.medicalclinic.controller;

import com.FKFabian.medicalclinic.model.PatientCreateDto;
import com.FKFabian.medicalclinic.model.PatientDTO;
import com.FKFabian.medicalclinic.service.PatientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {
    @MockBean
    PatientService patientService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getPatients_dataCorrect_PatientReturn() throws Exception {
        when(patientService.getPatients()).thenReturn(List.of(createPatientDto1(), createPatientDto2()));
        mockMvc.perform(get("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("111"));
    }

    @Test
    void getPatient_dataCorrect_PatientReturn() throws Exception {
        String email = "111";
        when(patientService.getPatient(email)).thenReturn(createPatientDto1());
        mockMvc.perform(get("/patients/111")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("111"));
    }

    @Test
    void delete_correctData_deletePatient() throws Exception {
        mockMvc.perform(delete("/patients/email@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());
    }

    @Test
    void addPatient_correctData_ReturnPatient() throws Exception {
        when(patientService.addPatient(creatorPatientCreateDto())).thenReturn(createPatientDto1());
        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(creatorPatientCreateDto()))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("111"));
    }

    @Test
    void updatePatient_correctData_ReturnPatient() throws Exception {
        String email = "email@gmail.com";
        when(patientService.updatePatient(email, creatorPatientCreateDto())).thenReturn(createPatientDto2());
        mockMvc.perform(put("/patients/email@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creatorPatientCreateDto()))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.lastName").value("222"));
    }

    @Test
    void updatePassword_correctData_ReturnPatient() throws Exception {
        String email = "111";
        String newPassword = "newPass";
        when(patientService.updatePassword(email, newPassword)).thenReturn(createPatientDto1());
        mockMvc.perform(patch("/patients/111")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPassword)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("111"));
    }

    private static PatientCreateDto creatorPatientCreateDto() {
        return new PatientCreateDto("111", "111", "111"
                , "111", "111"
                , "111", LocalDate.now());
    }

    private static PatientCreateDto creatorPatientCreateDto2() {
        return new PatientCreateDto("email1", "password1", "idCard1"
                , "Name1", "Surname1"
                , "phoneNumber1", LocalDate.now());
    }

    private static PatientDTO createPatientDto1() {
        return new PatientDTO("111", "111"
                , "111", "111", "111"
                , LocalDate.now());
    }

    private static PatientDTO createPatientDto2() {
        return new PatientDTO("222", "222"
                , "222", "222", "222"
                , LocalDate.now());
    }
}
