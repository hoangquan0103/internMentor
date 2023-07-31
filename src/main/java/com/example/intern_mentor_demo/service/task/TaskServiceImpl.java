package com.example.intern_mentor_demo.service.task;

import com.example.intern_mentor_demo.entity.task.Task;
import com.example.intern_mentor_demo.repositories.taskRepository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepo;
    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepo.findById(id);
    }

    @Override
    public List<Task> getTaskByTitle(String title){
        return taskRepo.findTaskByTitle(title);
    }

    @Override
    public Task createTask(Task task) {
        task.setCreatedDate(LocalDate.now());
        return taskRepo.save(task);
    }

    @Override
    public Task updateTask(Task updatedTask) {
        Optional<Task> task = taskRepo.findById(updatedTask.getId());
        if(task.isPresent()){
            Task newTask = task.get();
            newTask.setTitle(updatedTask.getTitle());
            newTask.setDescription(updatedTask.getDescription());
            newTask.setCreatedDate(updatedTask.getCreatedDate());
            newTask.setEstimateTime(updatedTask.getEstimateTime());
            newTask.setStatus(updatedTask.getStatus());
            newTask.setMentor(updatedTask.getMentor());
            try{
                return taskRepo.save(newTask);
            }catch (Exception e){
                return null;
            }
        }else {
            return null;
        }
    }
    @Override
    public boolean exists (long id){
        return taskRepo.existsById(id);
    }
    @Override
    public void deleteTask(long id) {
        taskRepo.deleteById(id);
    }
}
