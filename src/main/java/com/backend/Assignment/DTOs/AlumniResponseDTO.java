package com.backend.Assignment.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlumniResponseDTO {
    private String name;
    private String currentPost;
    private String university;
    private String location;
    private String linkedinHeadline;
    private Integer passoutYear;
}
