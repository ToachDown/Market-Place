package com.example.securitygateway.repository;

import com.example.securitygateway.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    public Optional<Person> findPersonByEmail(String email);
}
