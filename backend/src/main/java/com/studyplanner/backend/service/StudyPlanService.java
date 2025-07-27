package com.studyplanner.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyplanner.backend.model.StudyPlan;
import com.studyplanner.backend.model.User;
import com.studyplanner.backend.repository.StudyPlanRepository;
import com.studyplanner.backend.repository.UserRepository;

@Service
public class StudyPlanService {

    @Autowired
    private StudyPlanRepository studyPlanRepository;

    @Autowired
    private UserRepository userRepository;

    public StudyPlan addStudyPlan(StudyPlan studyPlan) {
        return studyPlanRepository.save(studyPlan);
    }

    public List<StudyPlan> getAllPlans() {
        return studyPlanRepository.findAll();
    }

    public StudyPlan updatePlan(Long id, StudyPlan studyPlan) {
        StudyPlan existing = studyPlanRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setSubject(studyPlan.getSubject());
            existing.setTopic(studyPlan.getTopic());
            existing.setStartDate(studyPlan.getStartDate());
            existing.setEndDate(studyPlan.getEndDate());
            return studyPlanRepository.save(existing);
        }
        return null;
    }

    public StudyPlan markTaskAsCompleted(Long id) {
    StudyPlan plan = studyPlanRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Plan not found with id " + id));
    plan.setCompleted(true); // ✅ mark as completed
    return studyPlanRepository.save(plan);
}

    public boolean deletePlan(Long id) {
        if (studyPlanRepository.existsById(id)) {
            studyPlanRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ✅ ADD THIS METHOD
    public List<StudyPlan> getPlansByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return List.of(); // return empty list if user doesn't exist
        return studyPlanRepository.findByUser(user);
    }
}
