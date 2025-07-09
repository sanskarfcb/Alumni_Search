package com.backend.Assignment.Service;

import com.backend.Assignment.DTOs.AlumniResponseDTO;
import com.backend.Assignment.DTOs.AlumniSearchDTO;
import com.backend.Assignment.Model.AlumniProfile;
import com.backend.Assignment.Repo.AlumniRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlumniServiceImplementation implements AlumniService {

    private final AlumniRepo alumniRepo;

    public List<AlumniResponseDTO> searchAndSaveAlumni(AlumniSearchDTO searchDTO) {

        List<AlumniProfile> profiles = Arrays.asList(
                AlumniProfile.builder()
                        .name("John Doe")
                        .currentPost(searchDTO.getDesignation())
                        .university(searchDTO.getUniversity())
                        .location("New York, NY")
                        .linkedinHeadline("Passionate Software Engineer at XYZ Corp")
                        .passoutYear(searchDTO.getPassoutYear())
                        .build(),

                AlumniProfile.builder()
                        .name("Jane Smith")
                        .currentPost(searchDTO.getDesignation())
                        .university(searchDTO.getUniversity())
                        .location("San Francisco, CA")
                        .linkedinHeadline("Data Scientist | AI Enthusiast")
                        .passoutYear(searchDTO.getPassoutYear() != null ? searchDTO.getPassoutYear() - 1 : null)
                        .build()
        );

        List<AlumniProfile> saved = alumniRepo.saveAll(profiles);

        return saved.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AlumniResponseDTO> getAllAlumni() {
        return alumniRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private AlumniResponseDTO mapToDTO(AlumniProfile profile) {
        return AlumniResponseDTO.builder()
                .name(profile.getName())
                .currentPost(profile.getCurrentPost())
                .university(profile.getUniversity())
                .location(profile.getLocation())
                .linkedinHeadline(profile.getLinkedinHeadline())
                .passoutYear(profile.getPassoutYear())
                .build();
    }
}
