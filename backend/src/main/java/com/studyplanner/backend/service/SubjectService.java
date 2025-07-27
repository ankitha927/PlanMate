package com.studyplanner.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyplanner.backend.model.Subject;
import com.studyplanner.backend.model.User;
import com.studyplanner.backend.repository.SubjectRepository;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepo;

    public Subject addSubject(Subject subject) {
        return subjectRepo.save(subject);
    }

    public List<Subject> getSubjects(User user) {
        return subjectRepo.findByUser(user);
    }
}
