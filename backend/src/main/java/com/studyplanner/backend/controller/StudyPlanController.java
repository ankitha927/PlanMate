package com.studyplanner.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyplanner.backend.model.StudyPlan;
import com.studyplanner.backend.service.StudyPlanService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/plans")
public class StudyPlanController {

    @Autowired
    private StudyPlanService studyPlanService;

    @PostMapping("/add")
public StudyPlan addStudyPlan(@RequestBody StudyPlan studyPlan) {
    if (studyPlan.getUser() == null || studyPlan.getUser().getId() == null) {
        throw new IllegalArgumentException("User ID is required");
    }
    return studyPlanService.addStudyPlan(studyPlan);
}
 

@PutMapping("/complete/{id}")
public StudyPlan markTaskAsCompleted(@PathVariable Long id) {
    return studyPlanService.markTaskAsCompleted(id);
}


    @GetMapping
    public List<StudyPlan> getAllPlans() {
        return studyPlanService.getAllPlans();
    }

    @PutMapping("/update/{id}")
    public StudyPlan updatePlan(@PathVariable Long id, @RequestBody StudyPlan studyPlan) {
        return studyPlanService.updatePlan(id, studyPlan);
    }
    @GetMapping("/user/{userId}")
public List<StudyPlan> getPlansByUser(@PathVariable Long userId) {
    return studyPlanService.getPlansByUserId(userId);
}


    @DeleteMapping("/delete/{id}")
    public String deletePlan(@PathVariable Long id) {
        return studyPlanService.deletePlan(id) ? "Deleted" : "Plan not found";
    }
}
