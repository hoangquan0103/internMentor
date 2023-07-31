package com.example.intern_mentor_demo.controller.userController;

import com.example.intern_mentor_demo.entity.ResponseDTO;
import com.example.intern_mentor_demo.entity.user.Intern;
import com.example.intern_mentor_demo.service.userService.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/intern")
public class InternController {
    @Autowired
    InternService internService;
    @GetMapping
    ResponseEntity<ResponseDTO> findAllIntern(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO<>("200", "Found All Intern successfully", internService.findAllIntern())
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findOneInternById(@PathVariable("id") Long id){
        Optional<Intern> foundIntern = internService.findOneInternById(id);
        if (foundIntern.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO<>("200","Found successfully",foundIntern)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDTO<>("404","Cannot found intern with id: " + id," ")
            );
        }
    }
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveIntern (@RequestBody Intern intern){
        List<Intern> interns = internService.findOneInternByName(intern);
        if(interns.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseDTO<>("501","Intern name already taken"," ")
            );
        }
        Intern newIntern = internService.createIntern(intern);
        if(newIntern!= null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO<>("200","Created successfully",newIntern)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseDTO<>("501","Something wrong"," ")
        );
    }
    @PutMapping("/update")
    ResponseEntity<ResponseDTO>updateIntern(@RequestBody Intern newIntern){
        Intern intern = internService.updateIntern(newIntern);
        if(intern == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDTO<>("404","Not found intern to update, please insert"," ")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO<>("200","Update Intern successfully",intern)
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDTO> deleteIntern(@PathVariable("id") Long id){
        boolean exists = internService.exists(id);
        if(exists){
            internService.deleteIntern(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO<>("200","Delete intern successfully"," ")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseDTO<>("404","Cannot found intern to delete"," ")
        );
    }
}
