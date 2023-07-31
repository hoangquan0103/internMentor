package com.example.intern_mentor_demo.entity.user;

import com.example.intern_mentor_demo.entity.task.Task;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Intern extends User {
    private String mail;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "intern_task",
            joinColumns =
            @JoinColumn(name = "intern_id"),
            inverseJoinColumns =
            @JoinColumn(name = "task_id"))
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    private Mentor mentor;

}
