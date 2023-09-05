package br.com.senai.springsecurityjava.security.util;

import br.com.senai.springsecurityjava.model.entity.Person;
import br.com.senai.springsecurityjava.repository.PersonRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static final String STRONGPASSWORD = "c127a7b6adb013a5ff879ae71afa62afa4b4ceb72afaa54711dbcde67b6dc325";
    private static PersonRepository personRepository;

    @Autowired
    JWTUtil(PersonRepository personRepository){
        JWTUtil.personRepository = personRepository;
    }

    public static String generateToken(Person person) {
        Algorithm algorithm = Algorithm.HMAC256(STRONGPASSWORD);

        return JWT.create()
                .withIssuer("WEG")
                .withSubject(person.getId().toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + 1800000))
                .sign(algorithm);
    }

    public static Person getUsuario(String token) {
         String id = JWT.decode(token).getSubject();
         return personRepository.findById(Long.parseLong(id)).orElseThrow();
    }

}
