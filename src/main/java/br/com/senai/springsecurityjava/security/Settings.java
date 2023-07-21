package br.com.senai.springsecurityjava.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class Settings {

//    @Bean
//    UserDetailService - é a lógica para buscar os usuários
//    public UserDetailsService userDetailsService(){
//       List<UserDetails> users = new ArrayList<>();
//       PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//     User Details é uma interface que determina do tipo de usuário que o
//     Spring security vai usar, o usuário implementa o UserDetails
//        UserDetails user1 =
//      É um método que vai usar o contrutor para cria um usuário
//                User.builder()
//                        .username("Aninha")
//                        .password(
//                                passwordEncoder.encode("123")
//                        )
//                      Cria realmente o objeto
//                        .build();
//
//        UserDetails user2 =
//                User.builder()
//                        .username("Ana")
//                        .password(
//                                passwordEncoder.encode("123")
//                        )
//                        .build();
//
//        users.add(user1);
//        users.add(user2);
//
//        return new InMemoryUserDetailsManager(users);
//    }




}
