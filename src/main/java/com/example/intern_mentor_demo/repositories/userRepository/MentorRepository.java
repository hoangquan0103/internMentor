package com.example.intern_mentor_demo.repositories.userRepository;

import com.example.intern_mentor_demo.entity.user.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findMentorByName (String name);
}
