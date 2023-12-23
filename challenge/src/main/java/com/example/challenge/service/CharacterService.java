package com.example.challenge.service;

import com.example.challenge.model.CharacterEntity;
import com.example.challenge.model.User;
import com.example.challenge.repository.ICharacterRepository;
import com.example.challenge.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CharacterService {

    @Autowired
    private ICharacterRepository characterRepository;
    @Autowired
    private IUserRepository iUserRepository;

    // Crear
    public CharacterEntity createCharacter(CharacterEntity character, Long id) {
        User user = iUserRepository.findById(id).get();
        character.setUser(user);
        return characterRepository.save(character);
    }

    // traer todos
    public List<CharacterEntity> getAllCharacters(){
        return  characterRepository.findAll();
    }
    // traer uno por su id
    public CharacterEntity getCharacterById(Long id){
        return characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("character not found with id: " + id));
    }
    // actualizar uno por su id
    public CharacterEntity updateCharacter(Long id, CharacterEntity characterEntity) {
        CharacterEntity existingCharacter = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("character not found with id: " + id));

        return characterRepository.save(existingCharacter);
    }
    // eliminar uno por su id
    public void deleteCharacterById(Long id){
        characterRepository.deleteById(id);
    }
}
