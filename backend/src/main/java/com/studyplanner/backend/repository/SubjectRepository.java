package com.studyplanner.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studyplanner.backend.model.Subject;
import com.studyplanner.backend.model.User;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByUser(User user);
}
