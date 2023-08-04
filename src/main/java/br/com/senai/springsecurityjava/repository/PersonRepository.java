package br.com.senai.springsecurityjava.repository;

import br.com.senai.springsecurityjava.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
