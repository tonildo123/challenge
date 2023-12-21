package com.example.challenge.controller;

import com.example.challenge.model.CharacterEntity;
import com.example.challenge.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("getAll")
    @ResponseBody
    public List<CharacterEntity> getAll() {
        return characterService.getAllCharacters();
    }

    @GetMapping("getOne/{id}")
    @ResponseBody
    public ResponseEntity<CharacterEntity> getOne(@PathVariable Long id) {
        CharacterEntity charact = characterService.getCharacterById(id);

        if (charact != null) {
            return new ResponseEntity<>(charact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<CharacterEntity> createCharacter(@RequestBody CharacterEntity characterEntity){
        CharacterEntity userCreated = characterService.createCharacter(characterEntity);

        if (userCreated != null) {
            return new ResponseEntity<>(userCreated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id}")
    @ResponseBody
    public ResponseEntity<CharacterEntity> update(@RequestBody CharacterEntity characterEntity, @PathVariable Long id) {
        characterEntity.setId(id);
        CharacterEntity updatedCharacter = characterService.updateCharacter(id, characterEntity);
        if (updatedCharacter != null) {
            return new ResponseEntity<>(updatedCharacter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Long id) {
        characterService.deleteCharacterById(id);
        return new ResponseEntity<>("Personaje borrado", HttpStatus.OK);
    }
}
