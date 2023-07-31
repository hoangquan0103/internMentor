package com.example.intern_mentor_demo.service.userService;


import com.example.intern_mentor_demo.entity.user.Intern;

import java.util.List;
import java.util.Optional;


public interface InternService {
    List<Intern> findAllIntern();
    Optional<Intern> findOneInternById(Long id);
    List<Intern> findOneInternByName(Intern intern);
    Intern createIntern(Intern intern);
    Intern updateIntern(Intern intern);
    boolean exists (long id);
    void deleteIntern(long id);

}
