package com.backend.Assignment.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "alumni_profile")
public class AlumniProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String currentPost;
    private String university;
    private String location;
    private String linkedinHeadline;

    @Column(nullable = true)
    private Integer passoutYear;
}
