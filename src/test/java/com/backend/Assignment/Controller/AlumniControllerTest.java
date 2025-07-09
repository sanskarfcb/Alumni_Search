package com.backend.Assignment.Controller;

import com.backend.Assignment.DTOs.AlumniResponseDTO;
import com.backend.Assignment.DTOs.AlumniSearchDTO;
import com.backend.Assignment.Service.AlumniService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@WebMvcTest(AlumniController.class)
public class AlumniControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlumniService alumniService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSearchAlumni_ReturnsSuccess() throws Exception {
        AlumniSearchDTO searchDTO = new AlumniSearchDTO();
        searchDTO.setUniversity("IIT Bombay");
        searchDTO.setDesignation("Software Engineer");
        searchDTO.setPassoutYear(2020);

        List<AlumniResponseDTO> responseList = List.of(
                AlumniResponseDTO.builder()
                        .name("John Doe")
                        .currentPost("Software Engineer")
                        .university("IIT Bombay")
                        .location("New York")
                        .linkedinHeadline("Passionate Engineer")
                        .passoutYear(2020)
                        .build()
        );

        Mockito.when(alumniService.searchAndSaveAlumni(any())).thenReturn(responseList);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/alumni/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data[0].name").value("John Doe"));
    }

    @Test
    void testSearchAlumni_InvalidRequest_ReturnsBadRequest() throws Exception {
        AlumniSearchDTO invalidDTO = new AlumniSearchDTO();
        invalidDTO.setUniversity("");  // invalid
        invalidDTO.setDesignation(""); // invalid

        mockMvc.perform(MockMvcRequestBuilders.post("/api/alumni/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("error"))
                .andExpect(jsonPath("$.message").value("Validation failed"));
    }

    @Test
    void testGetAllAlumni_ReturnsSuccess() throws Exception {
        List<AlumniResponseDTO> alumniList = List.of(
                AlumniResponseDTO.builder()
                        .name("Alice")
                        .currentPost("Data Scientist")
                        .university("NIT")
                        .location("Delhi")
                        .linkedinHeadline("Data Wizard")
                        .passoutYear(2021)
                        .build()
        );

        Mockito.when(alumniService.getAllAlumni()).thenReturn(alumniList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/alumni/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data[0].name").value("Alice"));
    }
}