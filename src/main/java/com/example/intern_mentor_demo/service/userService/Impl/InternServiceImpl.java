package com.example.intern_mentor_demo.service.userService.Impl;

import com.example.intern_mentor_demo.entity.user.Intern;
import com.example.intern_mentor_demo.repositories.userRepository.InternRepository;
import com.example.intern_mentor_demo.service.userService.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternServiceImpl implements InternService {
    @Autowired
    InternRepository internRepo;

    @Override
    public List<Intern> findAllIntern() {
        return internRepo.findAll();
    }

    @Override
    public Optional<Intern> findOneInternById(Long id) {
        return internRepo.findById(id);
    }

    @Override
    public List<Intern> findOneInternByName(Intern intern) {
        return internRepo.findInternByName(intern.getName().trim());
    }

    @Override
    public Intern createIntern(Intern intern) {
        return internRepo.save(intern);
    }

    @Override
    public Intern updateIntern(Intern intern) {
        Optional<Intern> i = internRepo.findById(intern.getId());
        if(i.isPresent()){
            Intern newIntern = i.get();
            newIntern.setName(intern.getName());
            newIntern.setDob(intern.getDob());
            newIntern.setGender(intern.getGender());
            newIntern.setAddress(intern.getAddress());
            newIntern.setPhone(intern.getPhone());
            newIntern.setMail(intern.getMail());
            newIntern.setStartDate(intern.getStartDate());
            newIntern.setEndDate(intern.getEndDate());
            newIntern.setMentor(intern.getMentor());
//            newIntern.setTasks(intern.getTasks());
            try{
                return internRepo.save(newIntern);
            }catch (Exception e){
                return null;
            }
        }else {
            return null;

        }
    }

    @Override
    public boolean exists(long id) {
        return internRepo.existsById(id);
    }

    @Override
    public void deleteIntern(long id) {
        internRepo.deleteById(id);
    }

}
