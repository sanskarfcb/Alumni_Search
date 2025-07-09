package com.backend.Assignment.Controller;


import com.backend.Assignment.DTOs.AlumniResponseDTO;
import com.backend.Assignment.DTOs.AlumniSearchDTO;
import com.backend.Assignment.DTOs.ApiResponse;
import com.backend.Assignment.Service.AlumniService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumni")
@RequiredArgsConstructor

public class AlumniController {

    private final AlumniService alumniService;

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<AlumniResponseDTO>> searchAlumni(@Valid @RequestBody AlumniSearchDTO searchDTO) {
        List<AlumniResponseDTO> result = alumniService.searchAndSaveAlumni(searchDTO);
        return ResponseEntity.ok(ApiResponse.<AlumniResponseDTO>builder()
                .status("success")
                .message("Alumni fetched and saved successfully")
                .data(result)
                .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<AlumniResponseDTO>> getAllAlumni() {
        List<AlumniResponseDTO> result = alumniService.getAllAlumni();
        return ResponseEntity.ok(ApiResponse.<AlumniResponseDTO>builder()
                .status("success")
                .message("All alumni profiles retrieved")
                .data(result)
                .build());
    }
}
