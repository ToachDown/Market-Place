package com.example.securitygateway.entity;

import com.example.securitygateway.entity.enums.Role;
import com.example.securitygateway.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstname;
    private String lastname;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "rol")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

}
