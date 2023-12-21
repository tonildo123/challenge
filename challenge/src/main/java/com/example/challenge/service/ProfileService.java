package com.example.challenge.service;

import com.example.challenge.model.Profile;
import com.example.challenge.model.User;
import com.example.challenge.repository.IProfileRepository;
import com.example.challenge.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfileService {

    @Autowired
    private IProfileRepository iProfileRepository;

    @Autowired
    private IUserRepository iUserRepository;

    // Crear
    public ResponseEntity<String> createProfile(Profile profile) {

        Optional<User> userOptional = iUserRepository.findById(profile.getUser_id());

        if (userOptional.isPresent()) {

            System.out.println("existe el usuario ");

            Optional<Profile> existingProfile = iProfileRepository.findByUserId(profile.getUser_id());

            if (existingProfile.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Ya existe un perfil para el usuario con id: " + profile.getUser_id());
            } else {
                System.out.println("existe el usuario pero no el perfil");
                iProfileRepository.save(profile);
                return ResponseEntity.status(HttpStatus.CREATED).body("Perfil creado exitosamente");
            }
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado con id: " + profile.getUser_id());
        }
    }

    // traer todos
    public List<Profile> getAllProfiles(){
        return  iProfileRepository.findAll();
    }
    // traer uno por su id
    public Profile getProfileById(Long id){
        return iProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
    }
    // actualizar uno por su id
    public Profile updateProfile(Long id, Profile profile) {
        Profile existingProfile = iProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));

        return iProfileRepository.save(existingProfile);
    }
    // eliminar uno por su id
    public void deleteProfileById(Long id){
        iProfileRepository.deleteById(id);
    }


}
