package com.studyplanner.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyplanner.backend.model.Task;
import com.studyplanner.backend.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;

    public Task addTask(Task task) {
        return taskRepo.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task updateTask(Long id, Task updatedTask) {
        return taskRepo.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setDueDate(updatedTask.getDueDate());
            task.setCompleted(updatedTask.isCompleted());
            return taskRepo.save(task);
        }).orElse(null);
    }

    public boolean deleteTask(Long id) {
        if (taskRepo.existsById(id)) {
            taskRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
