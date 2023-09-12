package br.com.senai.springsecurityjava.util;

import br.com.senai.springsecurityjava.model.entity.Person;
import br.com.senai.springsecurityjava.model.entity.Profile;
import br.com.senai.springsecurityjava.repository.PersonRepository;
import br.com.senai.springsecurityjava.security.model.User;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DBUtil {

    private PersonRepository personRepository;

    @PostConstruct
    public void fillDb(){
        personRepository.deleteAll();

        /*ADMIN*/
        Person personAdmin = new Person();
        personAdmin.setUsername("admin");
        personAdmin.setFullname("admin");
        personAdmin.setPassword(new BCryptPasswordEncoder().encode("admin"));
        personAdmin.setAuthorities(List.of(Profile.ADMIN));
        personRepository.save(personAdmin);

        /*ADMIN*/
        User userAdmin = new User();
        userAdmin.setPerson(personAdmin);
        userAdmin.setAccountNonExpired(true);
        userAdmin.setAccountNonLocked(true);
        userAdmin.setCredentialsNonExpired(true);
        userAdmin.setEnabled(true);

        /*SELLER*/
        Person personSeller = new Person();
        personSeller.setUsername("seller");
        personSeller.setFullname("seller");
        personSeller.setPassword(new BCryptPasswordEncoder().encode("seller"));
        personSeller.setAuthorities(List.of(Profile.SELLER));
        personRepository.save(personSeller);

        /*SELLER*/
        User userSeller = new User();
        userSeller.setPerson(personSeller);
        userSeller.setAccountNonExpired(true);
        userSeller.setAccountNonLocked(true);
        userSeller.setCredentialsNonExpired(true);
        userSeller.setEnabled(true);

        /*CLIENT*/
        Person personClient = new Person();
        personClient.setUsername("client");
        personClient.setFullname("client");
        personClient.setPassword(new BCryptPasswordEncoder().encode("client"));
        personClient.setAuthorities(List.of(Profile.CLIENT));
        personRepository.save(personClient);

        /*CLIENT*/
        User userClient = new User();
        userClient.setPerson(personClient);
        userClient.setAccountNonExpired(true);
        userClient.setAccountNonLocked(true);
        userClient.setCredentialsNonExpired(true);
        userClient.setEnabled(true);
    }

}
