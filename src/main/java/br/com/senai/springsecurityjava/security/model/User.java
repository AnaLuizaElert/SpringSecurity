package br.com.senai.springsecurityjava.security.model;

import br.com.senai.springsecurityjava.model.entity.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

//    Fazendo dessa maneira não há persistência de dados! Se você precisar desses dados não dá para fazer dessa maneira
//    únicos dados salvos aqui é a senha e o email.
//    Para manter a persistência basta colocar essa entidade como entity e abrir um relacionamento com o person de @OnetoOne

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Person person;
    private String password;
    private Collection<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @Override
    public String getUsername() {
        return person.getEmail();
    }

}
