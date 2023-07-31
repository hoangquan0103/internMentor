package com.example.intern_mentor_demo.service.userService;

import com.example.intern_mentor_demo.entity.user.Intern;
import com.example.intern_mentor_demo.entity.user.Mentor;

import java.util.List;
import java.util.Optional;

public interface MentorService {
    List<Mentor> findAllMentor();
    Optional<Mentor> findOneMentorById(Long id);
    List<Mentor> findOneMentorByName(Mentor mentor);
    Mentor createMentor(Mentor mentor);
    Mentor updateMentor(Mentor mentor);
//    Mentor changeInternToAnotherMentor(Intern intern, Mentor mentor);
    boolean exists (long id);
    void deleteMentor(long id);

}
