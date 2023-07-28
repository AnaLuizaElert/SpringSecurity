package br.com.senai.springsecurityjava.repository;

import br.com.senai.springsecurityjava.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String user);
}
