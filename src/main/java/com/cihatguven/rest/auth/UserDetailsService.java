package com.cihatguven.rest.auth;

import com.cihatguven.rest.model.Person;
import com.cihatguven.rest.model.enums.Role;
import com.cihatguven.rest.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        List<Person> personList = personRepository.findAll();
        if (personList.size() == 0) {
            Person person = new Person();
            person.setUsername("admin");
            person.setPassword(passwordEncoder.encode("1234"));
            person.setRole(Role.ADMIN);
            personRepository.save(person);
        }

//        users.put("temelt", passwordEncoder.encode("123"));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> optionalPerson = personRepository.findByUsername(username);
        Person person = optionalPerson.orElseThrow(() -> new EntityNotFoundException("kullanıcı bulunamadı."));
        return new CustomUserDetails(person);

    }
}