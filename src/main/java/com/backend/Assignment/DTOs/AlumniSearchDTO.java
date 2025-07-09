package com.backend.Assignment.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AlumniSearchDTO {
    @NotBlank(message = "University is required")
    private String university;
    @NotBlank(message = "Designation is required")
    private String designation;
    private Integer passoutYear;
}
