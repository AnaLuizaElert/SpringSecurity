package br.com.senai.springsecurityjava.util;

import br.com.senai.springsecurityjava.model.entity.Profile;
import br.com.senai.springsecurityjava.model.entity.User;
import br.com.senai.springsecurityjava.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DBUtil {

    private UserRepository userRepository;

    @PostConstruct
    public void fillDb(){
        userRepository.deleteAll();

        /*ADMIN*/
        User userAdmin = new User();
        userAdmin.setUsername("admin");
        userAdmin.setFullname("admin");
        userAdmin.setPassword(new BCryptPasswordEncoder().encode("admin"));
        userAdmin.setEnabled(true);
        userAdmin.setAccountNonExpired(true);
        userAdmin.setCredentialsNonExpired(true);
        userAdmin.setAccountNonLocked(true);
        userAdmin.setAuthorities(List.of(Profile.ADMIN));
        userRepository.save(userAdmin);

        /*SELLER*/
        User userSeller = new User();
        userSeller.setUsername("seller");
        userSeller.setFullname("seller");
        userSeller.setPassword(new BCryptPasswordEncoder().encode("seller"));
        userSeller.setEnabled(true);
        userSeller.setAccountNonExpired(true);
        userSeller.setCredentialsNonExpired(true);
        userSeller.setAccountNonLocked(true);
        userSeller.setAuthorities(List.of(Profile.SELLER));
        userRepository.save(userSeller);

        /*CLIENT*/
        User userClient = new User();
        userClient.setUsername("client");
        userClient.setFullname("client");
        userClient.setPassword(new BCryptPasswordEncoder().encode("client"));
        userClient.setEnabled(true);
        userClient.setAccountNonExpired(true);
        userClient.setCredentialsNonExpired(true);
        userClient.setAccountNonLocked(true);
        userClient.setAuthorities(List.of(Profile.CLIENT));
        userRepository.save(userClient);
    }

}
