package br.com.senai.springsecurityjava.security.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class Login {

    private String password;
    private String username;


}
