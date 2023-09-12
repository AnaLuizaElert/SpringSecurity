package br.com.senai.springsecurityjava.security.util;

import br.com.senai.springsecurityjava.model.entity.Person;
import br.com.senai.springsecurityjava.repository.PersonRepository;
import br.com.senai.springsecurityjava.security.model.User;
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
    JWTUtil(PersonRepository userRepository){
        JWTUtil.personRepository = userRepository;
    }

    public static String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(STRONGPASSWORD);

        return JWT.create()
                .withIssuer("WEG")
                .withSubject(user.getPerson().getId().toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + 1800000))
                .sign(algorithm);
    }

    public static User getUsuario(String token) {
         String id = JWT.decode(token).getSubject();
         Long idLong = Long.parseLong(id);
         Person person = personRepository.findById(idLong).orElseThrow();
         return new User(person);
    }

}
