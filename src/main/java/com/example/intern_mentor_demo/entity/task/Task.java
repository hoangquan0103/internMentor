package com.example.intern_mentor_demo.entity.task;

import com.example.intern_mentor_demo.entity.user.Intern;
import com.example.intern_mentor_demo.entity.user.Mentor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private LocalDate createdDate;
    private LocalDateTime estimateTime;
    private String status;
//    private Status status;

    @ManyToMany(mappedBy = "tasks", cascade = CascadeType.ALL)
//    @JsonBackReference
    private List<Intern> interns = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    private Mentor mentor;

}
//enum Status{
//    DOING,
//    DONE
//}
