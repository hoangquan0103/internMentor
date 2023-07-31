package com.example.intern_mentor_demo.controller.taskController;

import com.example.intern_mentor_demo.entity.ResponseDTO;
import com.example.intern_mentor_demo.entity.task.Task;

import com.example.intern_mentor_demo.service.task.TaskService;
import com.example.intern_mentor_demo.service.task.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/save")
    ResponseEntity<ResponseDTO> createTask( @RequestBody Task task) {
        Optional<Task> tasks = taskService.getTaskById(task.getId());
        if(tasks.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseDTO<>("501","Task name already taken"," ")
            );
        }
        Task newTask = taskService.createTask(task);
        if(newTask!= null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO<>("200","Found successfully",newTask)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseDTO<>("501","Something wrong"," ")
        );
    }

    @GetMapping
    ResponseEntity<ResponseDTO> getAllTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO<>("200","Found all task successfully",taskService.getAllTasks()));
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseDTO> getTaskById(@PathVariable("id") long id) {
        Optional<Task> foundTask = taskService.getTaskById(id);
        if(foundTask.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO<>("200","Found successfully",foundTask));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDTO<>("404","Cannot find task with id: " + id," "));
        }
    }

    @GetMapping("/get")
    ResponseEntity<ResponseDTO> getTaskByName(@RequestParam("title") String title){
        List<Task> tasks = taskService.getTaskByTitle(title);
        if(tasks.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO<>("404","Cannot find task with title: " + title," "));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseDTO<>("200","Found successfully",tasks));
    }

    @PutMapping("/update")
    ResponseEntity<ResponseDTO> updateTask(@RequestBody Task task) {
        Task updatedTask = taskService.updateTask(task);
        if(updatedTask == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDTO<>("404","Not found task to update, please insert"," ")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO<>("200","Update task successfully",updatedTask)
        );
    }


    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDTO> deleteTask(@PathVariable long id) {
        boolean exists = taskService.exists(id);
        if(exists){
            taskService.deleteTask(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO<>("200","Delete intern successfully"," ")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseDTO<>("404","Cannot found intern to delete"," ")
        );
    }
    //advanced: note: xu ly xoa neu intern bi xoa, thi task se xu ly nhu nao, update lai task
}
