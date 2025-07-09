package com.backend.Assignment.Service;

import com.backend.Assignment.DTOs.AlumniResponseDTO;
import com.backend.Assignment.DTOs.AlumniSearchDTO;
import com.backend.Assignment.Model.AlumniProfile;
import com.backend.Assignment.Repo.AlumniRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlumniServiceImplementationTest {

    @Mock
    private AlumniRepo alumniRepo;

    @InjectMocks
    private AlumniServiceImplementation alumniService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchAndSaveAlumni() {
        AlumniSearchDTO searchDTO = new AlumniSearchDTO();
        searchDTO.setUniversity("IIT Kanpur");
        searchDTO.setDesignation("Software Engineer");
        searchDTO.setPassoutYear(2022);

        List<AlumniProfile> mockProfiles = Arrays.asList(
                AlumniProfile.builder()
                        .name("John Doe")
                        .currentPost("Software Engineer")
                        .university("IIT Kanpur")
                        .location("Delhi")
                        .linkedinHeadline("Engineer at Google")
                        .passoutYear(2022)
                        .build()
        );

        when(alumniRepo.saveAll(anyList())).thenReturn(mockProfiles);

        List<AlumniResponseDTO> result = alumniService.searchAndSaveAlumni(searchDTO);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    public void testGetAllAlumni() {
        List<AlumniProfile> alumniProfiles = Arrays.asList(
                AlumniProfile.builder()
                        .name("Jane Smith")
                        .currentPost("Data Analyst")
                        .university("NIT")
                        .location("Mumbai")
                        .linkedinHeadline("Analytics Expert")
                        .passoutYear(2020)
                        .build()
        );

        when(alumniRepo.findAll()).thenReturn(alumniProfiles);

        List<AlumniResponseDTO> result = alumniService.getAllAlumni();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Jane Smith", result.get(0).getName());
    }
}