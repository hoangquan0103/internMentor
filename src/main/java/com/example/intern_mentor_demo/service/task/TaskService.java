package com.example.intern_mentor_demo.service.task;

import com.example.intern_mentor_demo.entity.task.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> getAllTasks();
    Optional<Task> getTaskById(Long id);
    List<Task> getTaskByTitle(String title);
    Task createTask(Task task);
    Task updateTask(Task updatedTask);
    public boolean exists (long id);
    void deleteTask(long id);

}
