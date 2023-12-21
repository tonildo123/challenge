package com.example.challenge.repository;

import com.example.challenge.model.Profile;
import com.example.challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT p FROM Profile p WHERE p.user_id = :user_id")
    Optional<Profile> findByUserId(@Param("user_id") Long user_id);
}
