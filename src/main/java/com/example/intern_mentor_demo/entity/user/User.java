package com.example.intern_mentor_demo.entity.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column(name = "date_of_birth",nullable = false)
    private LocalDate dob;
    @Column
    private String gender;
    @Column
    private String address;
    @Column
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
//    @JsonManagedReference
    private Role role;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "pass_word")
    private String passWord;

    @Transient
    private int age;
    public int getAge() {
        int currentDate = LocalDate.now().getYear();
        return currentDate - dob.getYear();
    }
}

