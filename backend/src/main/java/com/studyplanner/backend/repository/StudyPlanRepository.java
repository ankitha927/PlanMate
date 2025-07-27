package com.studyplanner.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studyplanner.backend.model.StudyPlan;
import com.studyplanner.backend.model.User;

public interface StudyPlanRepository extends JpaRepository<StudyPlan, Long> {
    List<StudyPlan> findByUserAndIsCompleted(User user, boolean isCompleted);

    List<StudyPlan> findByUser(User user);
}
