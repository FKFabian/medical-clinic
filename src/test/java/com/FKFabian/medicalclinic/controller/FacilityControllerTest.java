package com.FKFabian.medicalclinic.controller;

import com.FKFabian.medicalclinic.model.FacilityCreateDto;
import com.FKFabian.medicalclinic.model.FacilityDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FacilityControllerTest {
    @MockBean
    FacilityController facilityController;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getFacilities_dataCorrect_ReturnFacility() throws Exception {
        when(facilityController.getFacilities()).thenReturn(List.of(createFacility1(), createFacility2()));

        mockMvc.perform(get("/facilities")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].city").value("111"))
                .andExpect(jsonPath("$[1].city").value("222"));
//                .andExpect(jsonPath("$[0].doctorsId[0].name").value(null));
    }

    @Test
    void getFacility_dataCorrect_ReturnFacility() throws Exception {
        Long id = 1L;
        when(facilityController.getFacility(id)).thenReturn(createFacility1());

        mockMvc.perform(get("/facilities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.city").value(111));
    }

    @Test
    void addFacility_correctData_ReturnFacility() throws Exception {
        when(facilityController.addFacility(createFacilityCreateDto())).thenReturn(createFacility1()); // to co return to sprawdzam w assercji

        mockMvc.perform(post("/facilities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createFacilityCreateDto()))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.city").value("111"));
    }

    private static FacilityDTO createFacility1() {
        return new FacilityDTO("111", "111", "111"
                , "111", "111", new ArrayList<>());
    }

    private static FacilityDTO createFacility2() {
        return new FacilityDTO("222", "222", "222"
                , "222", "222", new ArrayList<>());
    }

    private static FacilityCreateDto createFacilityCreateDto() {
        return new FacilityCreateDto("333", "333", "333", "333", "333");
    }

}
