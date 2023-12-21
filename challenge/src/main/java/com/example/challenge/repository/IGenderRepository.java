package com.example.challenge.repository;

import com.example.challenge.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGenderRepository extends JpaRepository<Gender, Long> {
}
