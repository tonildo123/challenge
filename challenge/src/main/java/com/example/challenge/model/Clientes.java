package com.example.challenge.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Entity
@Data
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;
    private Date brithdate;
    private String cuit;
    private String domicilio;
    private String phone;
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clientes clientes)) return false;
        return Objects.equals(getId(), clientes.getId()) && getCuit().equals(clientes.getCuit()) && getPhone().equals(clientes.getPhone()) && getEmail().equals(clientes.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCuit(), getPhone(), getEmail());
    }
}
