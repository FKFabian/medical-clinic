package com.FKFabian.medicalclinic.controller;

import com.FKFabian.medicalclinic.model.VisitCreateDto;
import com.FKFabian.medicalclinic.model.VisitDto;
import com.FKFabian.medicalclinic.service.VisitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VisitControllerTest {
    @MockBean
    VisitService visitService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getVisits_dataCorrect_ReturnVisits() throws Exception {
        int pageNumber = 0;
        int pageSize = 5;
        when(visitService.getVisits(pageNumber, pageSize))
                .thenReturn(List.of(createVisit1(), createVisit2()));
        mockMvc.perform(get("/visits")
                        .param("pageNumber", String.valueOf(pageNumber))
                        .param("pageSize", String.valueOf(pageSize))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].patientId").value(1));
    }

    @Test
    void addVisit_correctData_ReturnVisit() throws Exception {
        String email = "visit@email";
        var createVisitDto = createVisitCreateDto();
        when(visitService.addVisit(createVisitDto, email))
                .thenReturn(createVisit1());
        mockMvc.perform(post("/visits/{email}", email)
                        .content(objectMapper.writeValueAsString(createVisitDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.patientId").value(1));

    }

    private static VisitDto createVisit1() {
        return new VisitDto(LocalDateTime.of(2000, 1, 1, 1, 1),
                LocalDateTime.of(2000, 1, 1, 1, 2), 1L, 1L);
    }

    private static VisitDto createVisit2() {
        return new VisitDto(LocalDateTime.of(2222, 2, 2, 2, 2),
                LocalDateTime.of(2222, 2, 2, 2, 22), 2L, 2L);
    }

    private static VisitCreateDto createVisitCreateDto() {
        return new VisitCreateDto(LocalDateTime.of(2000, 1, 1, 1, 1),
                LocalDateTime.of(2000, 1, 1, 1, 2));
    }
}

