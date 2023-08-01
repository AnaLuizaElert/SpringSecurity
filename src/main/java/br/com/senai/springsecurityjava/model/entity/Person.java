package br.com.senai.springsecurityjava.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    private Long cpf;

    private String name;
    private String lastname;
    /*É o username o email*/
    private String email;
}
