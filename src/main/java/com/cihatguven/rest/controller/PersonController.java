package com.cihatguven.rest.controller;

import com.cihatguven.rest.auth.annotations.IsAuthenticated;
import com.cihatguven.rest.auth.annotations.OnlyAdmin;
import com.cihatguven.rest.auth.annotations.OnlyEmployee;
import com.cihatguven.rest.auth.SecurityContextUtil;
import com.cihatguven.rest.model.Person;
import com.cihatguven.rest.services.PersonManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@IsAuthenticated
@RequiredArgsConstructor
@RequestMapping("person")
public class PersonController {

    private final PersonManager personManager;

    private final SecurityContextUtil securityContextUtil;

    @OnlyAdmin
    @PostMapping
    public Person save(@RequestBody Person person){
        return personManager.savePerson(person);
    }

    @OnlyAdmin
    @GetMapping("/admin")
    public Person getAdmin(){
        return securityContextUtil.getAdmin();
    }

    @OnlyEmployee
    @GetMapping("/employee")
    public Person getEmployee(){
        return securityContextUtil.getEmployee();
    }

    @GetMapping("/user")
    public Person getCurrentUser(){
        return securityContextUtil.getCurrentPerson();
    }



}
