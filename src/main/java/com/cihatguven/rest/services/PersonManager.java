package com.cihatguven.rest.services;

import com.cihatguven.rest.model.Person;
import com.cihatguven.rest.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonManager {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final PersonRepository personRepository;
    public Person savePerson(Person person) {
        Person savePerson = new Person();
        savePerson.setUsername(person.getUsername());
        savePerson.setPassword(passwordEncoder.encode(person.getPassword()));
        savePerson.setRole(person.getRole());
        return personRepository.save(savePerson);
    }
}
