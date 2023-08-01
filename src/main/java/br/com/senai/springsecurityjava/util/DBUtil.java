package br.com.senai.springsecurityjava.util;

import br.com.senai.springsecurityjava.model.entity.Person;
import br.com.senai.springsecurityjava.repository.PersonRepository;
import br.com.senai.springsecurityjava.security.model.User;
import br.com.senai.springsecurityjava.security.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class DBUtil {

    private UserRepository userRepository;

    @PostConstruct
    public void fillDb(){
        userRepository.deleteAll();

        Person person = new Person();
        person.setCpf(12345678912l);
        person.setName("Admin");
        person.setLastname("system");
        person.setEmail("admin@gmail.com");
        person.setPassword(new BCryptPasswordEncoder().encode("admin"));

        User user = new User();
        user.setPerson(person);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setAuthorities(new ArrayList<>());

//      Como está com cascade typeAll em user é para ser criado a pessoa já
        userRepository.save(user);
    }

}
