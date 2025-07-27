package com.studyplanner.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyplanner.backend.model.Subject;
import com.studyplanner.backend.model.User;
import com.studyplanner.backend.repository.UserRepository;
import com.studyplanner.backend.service.SubjectService;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public Subject add(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }

    @GetMapping("/user/{userId}")
    public List<Subject> getSubjects(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return subjectService.getSubjects(user);
    }
}
