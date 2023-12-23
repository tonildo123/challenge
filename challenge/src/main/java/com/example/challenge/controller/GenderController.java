package com.example.challenge.controller;

import com.example.challenge.model.Gender;
import com.example.challenge.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/gender")
public class GenderController {

    @Autowired
    private GenderService genderService;


    @PostMapping("/create/{id}")
    public ResponseEntity<Gender> createGender(@RequestBody  Gender gender, @PathVariable Long id){
        Gender genderCreated = genderService.createGender(gender, id);

        if (genderCreated != null) {
            return new ResponseEntity<>(genderCreated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("getAll")
    public List<Gender> getAll() {
        return genderService.getAllGenders();
    }

    @GetMapping("getOne/{id}")
    public ResponseEntity<Gender> getOne(@PathVariable Long id) {
        Gender gender = genderService.getGenderById(id);

        if (gender != null) {
            return new ResponseEntity<>(gender, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Gender> update(@RequestBody Gender gender, @PathVariable Long id) {
        gender.setId(id);
        Gender updategender = genderService.updateGender(id, gender);
        if (updategender != null) {
            return new ResponseEntity<>(updategender, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        genderService.deleteGenderById(id);
        return new ResponseEntity<>("genero borrada", HttpStatus.OK);
    }
}
