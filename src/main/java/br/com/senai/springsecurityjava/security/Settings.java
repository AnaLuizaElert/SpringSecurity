package br.com.senai.springsecurityjava.security;

import br.com.senai.springsecurityjava.security.service.JpaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
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
        /*TODO: se descomenta aqui tem que comentar na Controller o @PreAuthorize*/
//        httpSecurity.authorizeHttpRequests((authorization) ->
//                authorization
////                        /teste -> limita a requisição para apenas o que tiver o get vazio
////                        /teste/* -> permite a requisição para uma barra a mais
////                        /teste/** -> permite requisição com quantidade indeterminada de barras
////                        /teste* -> permite para qualquer método a requisição
//                        .requestMatchers(HttpMethod.GET, "/teste/nautenticado").permitAll()
////                        .requestMatchers(HttpMethod.DELETE, "/user", "/user2").authenticated()
//                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
////                        anyrequest -> qualquer requisição fora essas terá que ser autenticada (authenticated)
//                        .requestMatchers(HttpMethod.GET, "/teste/autenticado/admin").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/teste/autenticado/seller").hasAuthority("SELLER")
//                        .requestMatchers(HttpMethod.GET, "/teste/autenticado/client").hasAuthority("CLIENT")
//                        .requestMatchers(HttpMethod.GET, "/teste/autenticado/admin&seller").hasAnyAuthority("ADMIN", "SELLER")
//                        .anyRequest().authenticated());

//      httpSecurity.httpBasic((basic) -> basic.)
//      httpSecurity.formLogin((custom) -> custom.loginPage("/login").permitAll());
//      httpSecurity.formLogin().loginPage("/login").permitAll();

        /*Faz com que não seja mantida uma sessão ativa, quem fará isso é o filter*/
        httpSecurity.sessionManagement(
                session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        /*É uma classe onde qualquer requisição vai passar pela classe que colocamos dentro dela*/
        httpSecurity.addFilterBefore(new Filter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers(header -> header.disable());
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }

//    Como na controller da autenticação precisa de um authenticationManager,
//    aqui nós estamos criando ele
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
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
