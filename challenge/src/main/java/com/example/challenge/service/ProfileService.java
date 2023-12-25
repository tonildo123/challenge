package com.example.challenge.service;

import com.example.challenge.model.Profile;
import com.example.challenge.repository.IProfileRepository;
import com.example.challenge.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Profile createProfile(Profile profile, Long id) {

        Optional<Profile> optionalProfile = iProfileRepository.findByUserId(id);

        if (optionalProfile.isPresent()) {
            return null;
        }else {
            profile.setUserId(id);
            return iProfileRepository.save(profile);
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

        existingProfile.setName(profile.getName());
        existingProfile.setPhoto(profile.getPhoto());
        existingProfile.setPhone(profile.getPhone());

        return iProfileRepository.save(existingProfile);
    }
    // eliminar uno por su id
    public void deleteProfileById(Long id){
        iProfileRepository.deleteById(id);
    }


}
