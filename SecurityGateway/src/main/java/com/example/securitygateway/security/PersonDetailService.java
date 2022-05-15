package com.example.securitygateway.security;


import com.example.securitygateway.entity.Person;
import com.example.securitygateway.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("personDetailsService")
@AllArgsConstructor
public class PersonDetailService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Person person = personRepository.findPersonByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        return DetailsPerson.fromPerson(person);
    }
}
