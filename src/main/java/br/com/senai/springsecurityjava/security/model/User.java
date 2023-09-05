package br.com.senai.springsecurityjava.security.model;

import br.com.senai.springsecurityjava.model.entity.Person;
import br.com.senai.springsecurityjava.model.entity.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToOne
    private Person person;

    @Enumerated(EnumType.STRING)
    private List<Profile> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public String getUsername() {
        return person.getUsername();
    }

    public String getPassword() {
        return person.getPassword();
    }

    public List<Profile> getAuthorities() {
        return person.getAuthorities();
    }
}
