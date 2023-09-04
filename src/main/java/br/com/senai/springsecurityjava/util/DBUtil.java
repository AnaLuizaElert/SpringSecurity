package br.com.senai.springsecurityjava.util;

import br.com.senai.springsecurityjava.model.entity.User;
import br.com.senai.springsecurityjava.model.enums.Perfil;
import br.com.senai.springsecurityjava.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DBUtil {

    private UserRepository userRepository;

    @PostConstruct
    public void fillDb(){

        userRepository.deleteAll();

        User admin = new User();
        admin.setUsername("admin");
        admin.setFullname("Administrator");
        admin.setAuthorities(List.of(Perfil.ADMIN));
        admin.setEnabled(true);
        admin.setAccountNonLocked(true);
        admin.setAccountNonExpired(true);
        admin.setCredentialsNonExpired(true);
        admin.setPassword(new BCryptPasswordEncoder().encode("123"));
        userRepository.save(admin);

        User seller = new User();
        seller.setUsername("seller");
        seller.setFullname("Seller");
        seller.setAuthorities(List.of(Perfil.SELLER));
        seller.setEnabled(true);
        seller.setAccountNonLocked(true);
        seller.setAccountNonExpired(true);
        seller.setCredentialsNonExpired(true);
        seller.setPassword(new BCryptPasswordEncoder().encode("123"));
        userRepository.save(seller);

        User client = new User();
        client.setUsername("client");
        client.setFullname("Client");
        client.setAuthorities(List.of(Perfil.CLIENT));
        client.setEnabled(true);
        client.setAccountNonLocked(true);
        client.setAccountNonExpired(true);
        client.setCredentialsNonExpired(true);
        client.setPassword(new BCryptPasswordEncoder().encode("123"));
        userRepository.save(client);
    }
}