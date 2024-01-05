package com.example.challenge.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ClienteDTO {

    private String name;
    private String lastname;
    private Date brithdate;
    private String cuit;
    private String domicilio;
    private String phone;
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(String name,
                      String lastname,
                      Date brithdate,
                      String cuit,
                      String domicilio,
                      String phone,
                      String email) {
        this.name = name;
        this.lastname = lastname;
        this.brithdate = brithdate;
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.phone = phone;
        this.email = email;
    }
}
