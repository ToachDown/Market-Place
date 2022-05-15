package com.example.securitygateway.controller;

import com.example.securitygateway.entity.Person;
import com.example.securitygateway.entity.dto.UserCreateDto;
import com.example.securitygateway.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/person")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    @PreAuthorize("hasAuthority('write')")
    public Person createPerson(@RequestBody UserCreateDto dto) {
        return personService.savePerson(dto);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('read')")
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('read')")
    public Person getPerson(@PathVariable UUID id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/username/{email}")
    @PreAuthorize("hasAuthority('read')")
    public Person getPersonByEmail(@PathVariable String email) {
        return personService.getPersonByEmail(email);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('write')")
    public void deletePerson(@PathVariable UUID id) {
        personService.deletePerson(id);
    }
}
