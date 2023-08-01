package br.com.senai.springsecurityjava.security.repository;

import br.com.senai.springsecurityjava.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByPerson_Email(String user);
}
