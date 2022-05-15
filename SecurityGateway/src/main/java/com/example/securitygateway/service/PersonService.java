package com.example.securitygateway.service;

import com.example.securitygateway.entity.Person;
import com.example.securitygateway.entity.enums.Status;
import com.example.securitygateway.entity.dto.UserCreateDto;
import com.example.securitygateway.exception.exceptions.UserNotFoundException;
import com.example.securitygateway.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public Person savePerson(UserCreateDto dto) {
        Person user = Person.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .role(dto.getRole())
                .status(Status.ACTIVE)
                .build();
        return personRepository.saveAndFlush(user);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %s not found", id)));
    }

    public Person getPersonByEmail(String email) {
        return personRepository.findPersonByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email %s not found", email)));
    }

    public void deletePerson(UUID id) {
        personRepository.deleteById(id);
    }
}
