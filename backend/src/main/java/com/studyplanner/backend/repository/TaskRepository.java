package com.studyplanner.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studyplanner.backend.model.Task;
import com.studyplanner.backend.model.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
