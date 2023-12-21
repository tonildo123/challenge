package com.example.challenge.service;

import com.example.challenge.model.Gender;
import com.example.challenge.model.Movie;
import com.example.challenge.repository.IGenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GenderService {
    @Autowired
    private IGenderRepository genderRepository;

    public Gender createGender(Gender gender) {

        return genderRepository.save(gender);
    }
    // traer todos
    public List<Gender> getAllGenders(){
        return  genderRepository.findAll();
    }
    // traer uno por su id
    public Gender getGenderById(Long id){
        return genderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("gender not found with id: " + id));
    }
    // actualizar uno por su id
    public Gender updateGender(Long id, Gender gender) {
        Gender existingGender = genderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("gender not found with id: " + id));

        return genderRepository.save(existingGender);
    }
    // eliminar uno por su id
    public void deleteGenderById(Long id){
        genderRepository.deleteById(id);
    }
}
