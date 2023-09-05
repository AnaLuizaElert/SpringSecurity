package br.com.senai.springsecurityjava.util;

import br.com.senai.springsecurityjava.model.entity.Profile;
import br.com.senai.springsecurityjava.model.entity.Person;
import br.com.senai.springsecurityjava.repository.PersonRepository;
import br.com.senai.springsecurityjava.security.model.User;
import br.com.senai.springsecurityjava.security.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DBUtil {

    private UserRepository userRepository;
    private PersonRepository personRepository;

    @PostConstruct
    public void fillDb(){
        personRepository.deleteAll();
        userRepository.deleteAll();

        /*ADMIN*/
        Person personAdmin = new Person();
        personAdmin.setFullname("admin");
        personRepository.save(personAdmin);

        User userAdmin = new User();
        userAdmin.setUsername("admin");
        userAdmin.setPassword(new BCryptPasswordEncoder().encode("admin"));
        userAdmin.setPerson(personAdmin);
        userAdmin.setAuthorities(List.of(Profile.ADMIN));
        userAdmin.setAccountNonExpired(true);
        userAdmin.setAccountNonLocked(true);
        userAdmin.setCredentialsNonExpired(true);
        userAdmin.setEnabled(true);
        userRepository.save(userAdmin);

        /*SELLER*/
        Person personSeller = new Person();
        personSeller.setFullname("seller");
        personRepository.save(personSeller);

        User userSeller = new User();
        userSeller.setUsername("seller");
        userSeller.setPerson(personSeller);
        userSeller.setPassword(new BCryptPasswordEncoder().encode("seller"));
        userSeller.setEnabled(true);
        userSeller.setAccountNonExpired(true);
        userSeller.setCredentialsNonExpired(true);
        userSeller.setAccountNonLocked(true);
        userSeller.setAuthorities(List.of(Profile.SELLER));
        userRepository.save(userSeller);

        /*CLIENT*/
        Person personClient = new Person();
        personClient.setFullname("client");
        personRepository.save(personClient);

        User userClient = new User();
        userClient.setUsername("client");
        userClient.setPerson(personClient);
        userClient.setPassword(new BCryptPasswordEncoder().encode("client"));
        userClient.setEnabled(true);
        userClient.setAccountNonExpired(true);
        userClient.setCredentialsNonExpired(true);
        userClient.setAccountNonLocked(true);
        userClient.setAuthorities(List.of(Profile.CLIENT));
        userRepository.save(userClient);
    }

}
