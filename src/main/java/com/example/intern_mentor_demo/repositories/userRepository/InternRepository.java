package com.example.intern_mentor_demo.repositories.userRepository;

import com.example.intern_mentor_demo.entity.user.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternRepository extends JpaRepository<Intern, Long> {
    List<Intern> findInternByName(String name);
}
