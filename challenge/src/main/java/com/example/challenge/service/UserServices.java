package com.example.challenge.service;

import com.example.challenge.model.User;
import com.example.challenge.repository.IUserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServices {
    @Autowired
    private IUserRepository iUserRepository;

    public User createUser(User user){
        Optional<User> optionalUser = iUserRepository.findByEmail(user.getEmail());

        if (optionalUser != null){
            return null;
        }else {
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
            return iUserRepository.save(user);
        }

    }
    public Optional<User> loginUser(User user) {
        Optional<User> optionalUser = iUserRepository.findByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            User storedUser = optionalUser.get();
            if (BCrypt.checkpw(user.getPassword(), storedUser.getPassword())) {
                return optionalUser;
            }
        }

        return Optional.empty();
    }
    public List<User> getAllUsers(){
        return iUserRepository.findAll();
    }
    public User getUserById(Long id){
        return iUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    public User updateUser(Long id, User updatedUser) {
        User existingUser = iUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }

        return iUserRepository.save(existingUser);
    }
    public void deleteUserById(Long id){
        iUserRepository.deleteById(id);
    }


}
