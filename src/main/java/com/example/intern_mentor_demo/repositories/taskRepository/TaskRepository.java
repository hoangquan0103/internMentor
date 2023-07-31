package com.example.intern_mentor_demo.repositories.taskRepository;

import com.example.intern_mentor_demo.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findTaskByTitle(String title);
}
