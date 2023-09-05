package br.com.senai.springsecurityjava.util;

import br.com.senai.springsecurityjava.model.entity.Profile;
import br.com.senai.springsecurityjava.model.entity.Person;
import br.com.senai.springsecurityjava.repository.PersonRepository;
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
        userRepository.deleteAll();

//        /*ADMIN*/
//        Person personAdmin = new Person();
//        personAdmin.setUsername("admin");
//        personAdmin.setFullname("admin");
//        personAdmin.setPassword(new BCryptPasswordEncoder().encode("admin"));
//        personAdmin.setEnabled(true);
//        personAdmin.setAccountNonExpired(true);
//        personAdmin.setCredentialsNonExpired(true);
//        personAdmin.setAccountNonLocked(true);
//        personAdmin.setAuthorities(List.of(Profile.ADMIN));
//        userRepository.save(personAdmin);
//
//        /*SELLER*/
//        Person personSeller = new Person();
//        personSeller.setUsername("seller");
//        personSeller.setFullname("seller");
//        personSeller.setPassword(new BCryptPasswordEncoder().encode("seller"));
//        personSeller.setEnabled(true);
//        personSeller.setAccountNonExpired(true);
//        personSeller.setCredentialsNonExpired(true);
//        personSeller.setAccountNonLocked(true);
//        personSeller.setAuthorities(List.of(Profile.SELLER));
//        userRepository.save(personSeller);
//
//        /*CLIENT*/
//        Person personClient = new Person();
//        personClient.setUsername("client");
//        personClient.setFullname("client");
//        personClient.setPassword(new BCryptPasswordEncoder().encode("client"));
//        personClient.setEnabled(true);
//        personClient.setAccountNonExpired(true);
//        personClient.setCredentialsNonExpired(true);
//        personClient.setAccountNonLocked(true);
//        personClient.setAuthorities(List.of(Profile.CLIENT));
//        userRepository.save(personClient);
    }

}
