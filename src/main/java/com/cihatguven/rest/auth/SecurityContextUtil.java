package com.cihatguven.rest.auth;

import com.cihatguven.rest.auth.exceptions.UnAuthorizedException;
import com.cihatguven.rest.model.Person;
import com.cihatguven.rest.model.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityContextUtil implements ApplicationContextAware {

    public Person getCurrentPerson(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication.getPrincipal() instanceof AnonymousAuthenticationToken) {
            throw new UnAuthorizedException("Lütfen giriş yapın");
        }
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getPerson();
    }

    public Person getAdmin() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (userDetails.getRole() == Role.ADMIN) {

            return userDetails.getPerson();

        } else {
            throw new UnAuthorizedException("Yanlış ROL!!!!!");
        }
    }

    public Person getEmployee() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (userDetails.getRole() == Role.EMPLOYEE) {

            return userDetails.getPerson();

        } else {
            throw new UnAuthorizedException("Yanlış ROL!!!!!");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
