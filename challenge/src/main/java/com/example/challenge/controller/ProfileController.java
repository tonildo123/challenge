package com.example.challenge.controller;

import com.example.challenge.dto.ProfileDTO;
import com.example.challenge.dto.UserDTO;
import com.example.challenge.model.Profile;
import com.example.challenge.model.User;
import com.example.challenge.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/profile/")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("getAll")
    @ResponseBody
    public List<Profile> getAll() {
        return profileService.getAllProfiles();
    }

    @GetMapping("getOne/{id}")
    @ResponseBody
    public ResponseEntity<Profile> getOne(@PathVariable Long id) {
        Profile profile = profileService.getProfileById(id);

        if (profile != null) {
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id}")
    @ResponseBody
    public ResponseEntity<Profile> update(@RequestBody Profile profile, @PathVariable Long id) {
        profile.setId(id);
        Profile updatedProfile = profileService.updateProfile(id, profile);
        if (updatedProfile != null) {
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("create/{id}")
    @ResponseBody
    public ResponseEntity<?> createProfile(@RequestBody Profile profile, @PathVariable Long id){

        Profile profileCreated = profileService.createProfile(profile, id);
        if (profileCreated != null) {
            ProfileDTO profileDTO = new ProfileDTO();

            profileDTO.setName(profileCreated.getName());
            profileDTO.setPhone(profileCreated.getPhone());
            profileDTO.setPhoto(profileCreated.getPhoto());

            return new ResponseEntity<>(profileDTO, HttpStatus.OK);
        } else {
            String errorMessage = "El perfil ya existe para el usuario con ID " + id;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Long id) {
        profileService.deleteProfileById(id);
        return new ResponseEntity<>("Profile borrado", HttpStatus.OK);
    }
}
