package com.example.securitygateway.entity.dto;

import com.example.securitygateway.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserCreateDto {

    @NotNull
    @Size(min = 2, max = 64)
    private String firstname;

    @Size(min = 2, max = 64)
    private String lastname;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 4, max = 32)
    private String password;

    @NotNull
    private Role role;
}
