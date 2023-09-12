package br.com.senai.springsecurityjava.security.service;

import br.com.senai.springsecurityjava.model.entity.Person;
import br.com.senai.springsecurityjava.repository.PersonRepository;
import br.com.senai.springsecurityjava.security.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JpaService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        return new User(person);
    }

}
