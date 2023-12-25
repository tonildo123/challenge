package com.example.challenge.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ProfileDTO {

    private String name;
    private String photo;
    private String phone;
    private Long userId;

    public ProfileDTO() {
    }

    public ProfileDTO(String name, String photo, String phone, Long userId) {
        this.name = name;
        this.photo = photo;
        this.phone = phone;
        this.userId = userId;
    }
}
