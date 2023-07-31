package com.example.intern_mentor_demo.entity.user;

import com.example.intern_mentor_demo.entity.task.Task;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Mentor extends User {
    @OneToMany(mappedBy = "mentor",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Task> tasks;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Intern> interns;
}
