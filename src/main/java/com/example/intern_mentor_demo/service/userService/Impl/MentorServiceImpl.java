package com.example.intern_mentor_demo.service.userService.Impl;

import com.example.intern_mentor_demo.entity.user.Intern;
import com.example.intern_mentor_demo.entity.user.Mentor;
import com.example.intern_mentor_demo.repositories.userRepository.InternRepository;
import com.example.intern_mentor_demo.repositories.userRepository.MentorRepository;
import com.example.intern_mentor_demo.service.userService.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MentorServiceImpl implements MentorService {
    @Autowired
    MentorRepository mentorRepo;

    @Autowired
    InternRepository internRepo;

    @Override
    public List<Mentor> findAllMentor() {
        return mentorRepo.findAll();
    }

    @Override
    public Optional<Mentor> findOneMentorById(Long id) {
        return mentorRepo.findById(id);
    }

    @Override
    public List<Mentor> findOneMentorByName(Mentor mentor) {
        return mentorRepo.findMentorByName(mentor.getName().trim());
    }

    @Override
    public Mentor createMentor(Mentor mentor) {
        return mentorRepo.save(mentor);
    }

    @Override
    public Mentor updateMentor(Mentor mentor) {
        Optional<Mentor> m = mentorRepo.findById(mentor.getId());
        if(m.isPresent()){
            Mentor newMentor = m.get();
            newMentor.setName(mentor.getName());
            newMentor.setDob(mentor.getDob());
            newMentor.setGender(mentor.getGender());
            newMentor.setAddress(mentor.getAddress());
            newMentor.setPhone(mentor.getPhone());
            try{
                return mentorRepo.save(newMentor);
            }catch (Exception e){
                return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public boolean exists(long id) {
        return mentorRepo.existsById(id);
    }

    @Override
    public void deleteMentor(long id) {
        mentorRepo.deleteById(id);
    }

    //    @Override
//    public Mentor addInternToMentor(Intern intern, Mentor mentor) {
//        Optional<Mentor> exitsMentor = mentorRepo.findById(mentor.getId());
//        if(exitsMentor.isPresent()){
//            Optional<Intern> exitsIntern = internRepo.findById(intern.getId());
//            if(exitsIntern.isPresent() && intern.getMentor() == null){
//                Mentor newMentor = exitsMentor.get();
//                newMentor.getInterns().add(exitsIntern.get());
//                try {
//                    return mentorRepo.save(newMentor);
//                }catch (Exception e){
//
//                    return null;
//                }
//            }else {
//                return mentorRepo.save(exitsMentor.get());
//            }
//        }else {
//            return null;
//        }
//    }
}
