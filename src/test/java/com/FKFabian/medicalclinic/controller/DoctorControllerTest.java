package com.FKFabian.medicalclinic.controller;

import com.FKFabian.medicalclinic.model.DoctorCreateDto;
import com.FKFabian.medicalclinic.model.DoctorDTO;
import com.FKFabian.medicalclinic.model.Facility;
import com.FKFabian.medicalclinic.service.DoctorService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DoctorControllerTest {
    @MockBean
    DoctorService doctorService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getDoctors_dataCorrect_ReturnDoctor() throws Exception {
        when(doctorService.getDoctors()).thenReturn(List.of(createDoctor1(), createDoctor2()));
        mockMvc.perform(get("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[1].surname").value("222"));
    }

    @Test
    void getDoctor_dataCorrect_ReturnDoctor() throws Exception {
        String email = "email@gmail.com";
        when(doctorService.getDoctor(email)).thenReturn(createDoctor1());
        mockMvc.perform(get("/doctors/email@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("111"));
    }

    @Test
    void addDoctor_dataCorrect_ReturnDoctor() throws Exception {
        when(doctorService.addDoctor(createDoctorCreateDto())).thenReturn(createDoctor1());
        mockMvc.perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDoctorCreateDto()))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("111"));
    }

    @Test
    void assignToFacility_dataCorrect_ReturnDoctor() throws Exception {
        String email = "email@gmail.com";
        Long id = 1L;
        when(doctorService.assignToFacility(email, id)).thenReturn(createDoctor1());
        mockMvc.perform(put("/doctors/email@gmail.com/facilities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("111"));
    }

    private static DoctorDTO createDoctor1() {
        return new DoctorDTO("111", "111", "111", "111"
                , new ArrayList<>());
    }

    private static DoctorDTO createDoctor2() {
        return new DoctorDTO("222", "222", "222", "222"
                , new ArrayList<>());
    }

    private static DoctorCreateDto createDoctorCreateDto() {
        return new DoctorCreateDto("333", "333"
                , "333", "333", "333");
    }
}
