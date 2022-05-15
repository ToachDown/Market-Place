package com.example.securitygateway.service;

import com.example.securitygateway.entity.Person;
import com.example.securitygateway.entity.dto.AuthDto;
import com.example.securitygateway.exception.exceptions.UserNotFoundException;
import com.example.securitygateway.repository.PersonRepository;
import com.example.securitygateway.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PersonRepository personRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void logoutPerson(HttpServletResponse response, HttpServletRequest request) {
        SecurityContextLogoutHandler handler = new SecurityContextLogoutHandler();
        handler.logout(request, response, null);
    }

    public ResponseEntity<?> loginPerson(AuthDto authDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword()));
            Person person = personRepository.findPersonByEmail(authDto.getEmail())
                    .orElseThrow(() -> new UserNotFoundException(String.format("User with email %s not found", authDto.getEmail())));
            String token = jwtTokenProvider.createToken(authDto.getEmail(), person.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", authDto.getEmail());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }
}
