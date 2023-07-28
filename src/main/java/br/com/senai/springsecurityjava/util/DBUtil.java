package br.com.senai.springsecurityjava.util;

import br.com.senai.springsecurityjava.model.entity.User;
import br.com.senai.springsecurityjava.repository.UserRepository;
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
        User user = new User();
        user.setUsername("admin");
        user.setFullname("Administrator");
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setAuthorities(new ArrayList<>());
        userRepository.save(user);
    }

}
