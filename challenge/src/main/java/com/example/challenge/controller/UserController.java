package com.example.challenge.controller;

import com.example.challenge.dto.UserDTO;
import com.example.challenge.model.User;
import com.example.challenge.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user/")
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping("getAll")
    @ResponseBody
    public ResponseEntity<List<UserDTO>> getAll() {
        List<User> users = userServices.getAllUsers();
        List<UserDTO> userDTOList = users.stream()
                .map(user -> new UserDTO(user.getId(), user.getEmail()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("getOne/{id}")
    @ResponseBody
    public ResponseEntity<UserDTO> getOne(@PathVariable Long id) {
        User user = userServices.getUserById(id);

        if (user != null) {
            UserDTO userDTO = new UserDTO(user.getId(), user.getEmail());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("auth/register")
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody User user){
        User userCreated = userServices.createUser(user);
        if (userCreated != null) {
            UserDTO userDTO = new UserDTO(userCreated.getId(), userCreated.getEmail());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            String errorMessage = "El usuario con el correo electrónico " + user.getEmail() + " ya está registrado.";
            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
        }
    }

    // En UserController
    @PostMapping("auth/login")
    @ResponseBody
    public ResponseEntity<UserDTO> login(@RequestBody User user) {
        Optional<User> loggedInUser = userServices.loginUser(user);

        return loggedInUser
                .map(u -> new ResponseEntity<>(new UserDTO(u.getId(), u.getEmail()), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    @PutMapping("update/{id}")
    @ResponseBody
    public ResponseEntity<UserDTO> update(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        User updatedUser = userServices.updateUser(id, user);
        if (updatedUser != null) {
            UserDTO userDTO = new UserDTO(updatedUser.getId(), updatedUser.getEmail());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userServices.deleteUserById(id);
        return new ResponseEntity<>("Usuario borrado", HttpStatus.OK);
    }


}
