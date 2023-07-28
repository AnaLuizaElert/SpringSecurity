package br.com.senai.springsecurityjava.security;

import br.com.senai.springsecurityjava.security.service.JpaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class Settings {

    private JpaService jpaService;

    @Autowired
    public void configure(AuthenticationManagerBuilder amb) throws Exception {
//        O NoOpPasswordEncoder nao criptografa
//        amb.userDetailsService(jpaService).passwordEncoder(NoOpPasswordEncoder.getInstance());

        // O BCryptPasswordEncoder criptografa a senha no bd
        amb.userDetailsService(jpaService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((authorization) ->
                authorization
//                        /teste -> limita a requisição para apenas o que tiver o get vazio
//                        /teste/* -> permite a requisição para uma barra a mais
//                        /teste/** -> permite requisição com quantidade indeterminada de barras
//                        /teste* -> permite para qualquer método a requisição
                        .requestMatchers(HttpMethod.GET, "/teste").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/user", "/user2").authenticated()
//                        anyrequest -> qualquer requisição fora essas terá que ser autenticada (authenticated)
                        .anyRequest().authenticated());

//        httpSecurity.httpBasic((basic) -> basic.)
//        httpSecurity.formLogin((custom) ->
//                custom.loginPage("/login").permitAll());
        httpSecurity.formLogin().permitAll();
//        criar
        return httpSecurity.build();
    }


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