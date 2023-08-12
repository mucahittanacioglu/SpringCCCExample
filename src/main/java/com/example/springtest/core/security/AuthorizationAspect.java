package com.example.springtest.core.security;

import com.example.springtest.core.IUserRepository;
import com.example.springtest.core.entities.Role;
import com.example.springtest.core.exceptions.UnauthenticatedException;
import com.example.springtest.core.exceptions.UnauthorizedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Aspect
@Component
public class AuthorizationAspect {

    @Autowired
    IUserRepository userRepository;

    @Before("@annotation(requiredRoles)")
    public void authorize(JoinPoint joinPoint, RequiredRoles requiredRoles) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // Implement your logic to fetch user roles from the database or wherever they are stored
            Set<Role> userRoles = getUserRoles(authentication.getName());
            //authentication.getPrincipal();
            // Check if the user has at least one of the required roles
            boolean hasRequiredRole = Arrays.stream(requiredRoles.value())
                    .anyMatch(userRoles::contains);

            if (!hasRequiredRole) {
                throw new UnauthorizedException("Unauthorized");
            }
        } else {
            throw new UnauthenticatedException("Unauthenticated");
        }
    }

    private Set<Role> getUserRoles(String email) {
        // Implement your logic to fetch user roles from the database or any other source
        // Return a Set<Role> containing the user's roles
        return userRepository.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found")).getRoles();
    }


}