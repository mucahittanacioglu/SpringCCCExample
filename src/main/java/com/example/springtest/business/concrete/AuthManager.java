package com.example.springtest.business.concrete;

import com.example.springtest.business.abstracts.IAuthService;
import com.example.springtest.business.dtos.UserLoginDto;
import com.example.springtest.business.dtos.UserRegisterDto;
import com.example.springtest.dataaccess.UserRepository;
import com.example.springtest.entity.User;
import com.ts.core.entities.Role;
import com.ts.core.repository.IUserRepository;
import com.ts.core.security.jwt.IJwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;


@Service
@RequiredArgsConstructor
public class AuthManager implements IAuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<String> signIn(UserLoginDto userLoginDto) throws Throwable {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));
        User tempUser = (User)userRepository.getUserByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var user = new org.springframework.security.core.userdetails.User(
                tempUser.getEmail(), tempUser.getPassword(), new ArrayList<>()
        );
        var jwt = jwtService.generateToken(user);
        return ResponseEntity.ok().body(jwt);
    }


    @Override
    @Transactional
    public ResponseEntity<String> signUp(UserRegisterDto userRegisterDto) {
        var user = org.springframework.security.core.userdetails.User.builder().username(userRegisterDto.getName())
                .password(passwordEncoder.encode(userRegisterDto.getPassword())).build();

        var userToSave = new User();

        userToSave.setName(userRegisterDto.getName());
        userToSave.setEmail(userRegisterDto.getEmail());
        userToSave.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        userToSave.setRoles(new HashSet<Role>());
        userToSave.addRole(new Role("ADD"));
        userToSave.addRole(new Role("UPDATE"));

//        userRepository.save(userToSave);
        userRepository.saveAndFlush(userToSave);
        var jwt = jwtService.generateToken(user);
        return ResponseEntity.ok().body(jwt);
    }
}
