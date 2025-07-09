package com.backend.Assignment.Service;

import com.backend.Assignment.DTOs.AlumniResponseDTO;
import com.backend.Assignment.DTOs.AlumniSearchDTO;

import java.util.List;

public interface AlumniService {
    List<AlumniResponseDTO> searchAndSaveAlumni(AlumniSearchDTO searchDTO);
    List<AlumniResponseDTO> getAllAlumni();

}
