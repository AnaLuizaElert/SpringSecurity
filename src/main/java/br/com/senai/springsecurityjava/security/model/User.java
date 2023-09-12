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
@NoArgsConstructor
public class User implements UserDetails {

    private Person person;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public User(Person person) {
        this.person = person;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

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
