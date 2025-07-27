package com.studyplanner.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studyplanner.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
