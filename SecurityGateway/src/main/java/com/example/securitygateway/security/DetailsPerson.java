package com.example.securitygateway.security;

import com.example.securitygateway.entity.Person;
import com.example.securitygateway.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Data
@AllArgsConstructor
public class DetailsPerson implements UserDetails {

    private final String username;
    private final String password;
    private final Set<SimpleGrantedAuthority> authorities;
    private final boolean active;

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public static UserDetails fromPerson(Person person) {
        return new User(
                person.getEmail(),
                person.getPassword(),
                person.getStatus().equals(Status.ACTIVE),
                person.getStatus().equals(Status.ACTIVE),
                person.getStatus().equals(Status.ACTIVE),
                person.getStatus().equals(Status.ACTIVE),
                person.getRole().getAuthorities()
        );
    }
}
