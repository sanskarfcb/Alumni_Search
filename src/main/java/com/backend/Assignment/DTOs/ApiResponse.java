package com.backend.Assignment.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiResponse<T> {
    private String status;
    private String message;
    private List<T> data;
}
