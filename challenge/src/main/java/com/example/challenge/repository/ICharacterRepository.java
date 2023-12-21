package com.example.challenge.repository;

import com.example.challenge.model.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICharacterRepository extends JpaRepository<CharacterEntity, Long> {
}
