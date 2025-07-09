package com.backend.Assignment.Repo;

import com.backend.Assignment.Model.AlumniProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumniRepo extends JpaRepository<AlumniProfile,Long> {

}
