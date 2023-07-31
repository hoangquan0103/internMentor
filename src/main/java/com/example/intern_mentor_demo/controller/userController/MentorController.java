package com.example.intern_mentor_demo.controller.userController;

import com.example.intern_mentor_demo.entity.ResponseDTO;
import com.example.intern_mentor_demo.entity.user.Intern;
import com.example.intern_mentor_demo.entity.user.InternMentorData;
import com.example.intern_mentor_demo.entity.user.Mentor;
import com.example.intern_mentor_demo.service.userService.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/mentor")
public class MentorController {
    @Autowired
    MentorService mentorService;

    @GetMapping
    ResponseEntity<ResponseDTO> findAllMentor(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO<>("200", "Found All Mentor Successfully",mentorService.findAllMentor() )
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findOneMentornById(@PathVariable("id") Long id){
        Optional<Mentor> foundMentor = mentorService.findOneMentorById(id);
        if (foundMentor.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO<>("200","Found successfully",foundMentor)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDTO<>("404","Cannot found intern with id: " + id," ")
            );
        }
    }
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveMentor(@RequestBody Mentor mentor){
        List<Mentor> mentors = mentorService.findOneMentorByName(mentor);
        if(mentors.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseDTO<>("501","Intern name already taken"," ")
            );
        }
        Mentor newMentor = mentorService.createMentor(mentor);
        if(newMentor!= null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO<>("200","Found successfully",newMentor)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseDTO<>("501","Something wrong"," ")
        );
    }
    @PutMapping("/update")
    ResponseEntity<ResponseDTO>updateIntern(@RequestBody Mentor newMentor){
        Mentor mentor = mentorService.updateMentor(newMentor);
        if(mentor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDTO<>("404","Not found intern to update, please insert"," ")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO<>("200","Update Intern successfully",mentor)
        );
    }

//    @PutMapping("/addInterns")
//    public ResponseEntity<ResponseDTO> addInternsToMentor(@RequestBody InternMentorData data) {
//        Intern intern = data.getIntern();
//        Mentor mentor = data.getMentor();
//        Mentor newMentor = mentorService.addInternToMentor(intern, mentor);
//        if (newMentor == null) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
//                    new ResponseDTO<>("204", "Please check what you inserted", " ")
//            );
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseDTO<>("200", "Add intern successfully", newMentor)
//        );
//    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDTO> deleteMentor(@PathVariable("id") Long id){
        boolean exists = mentorService.exists(id);
        if(exists){
            mentorService.deleteMentor(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO<>("200","Delete intern successfully"," ")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseDTO<>("404","Cannot found intern to delete"," ")
        );
    }
}
