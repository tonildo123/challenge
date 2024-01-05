package com.example.challenge.repository;

import com.example.challenge.model.Clientes;
import com.example.challenge.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClientesRepository extends JpaRepository<Clientes, Long> {

    Optional<Clientes> findById(Long id);
    Optional<Clientes> findByName(String name);
}
