package com.example.challenge.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String photo;
    private String phone;
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile profile)) return false;
        return getName().equals(profile.getName()) && Objects.equals(getPhone(), profile.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhone());
    }
}
