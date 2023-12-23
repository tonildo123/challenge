package com.example.challenge.repository;

import com.example.challenge.model.Profile;
import com.example.challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IProfileRepository extends JpaRepository<Profile, Long> {

}
