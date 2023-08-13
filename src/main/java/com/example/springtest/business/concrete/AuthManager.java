package com.example.springtest.business.concrete;

import com.example.springtest.business.abstracts.IAuthService;
import com.example.springtest.business.dtos.UserLoginDto;
import com.example.springtest.business.dtos.UserRegisterDto;
import com.example.springtest.core.IUserRepository;
import com.example.springtest.core.entities.Role;
import com.example.springtest.core.entities.User;
import com.example.springtest.core.security.jwt.IJwtService;
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
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<String> signIn(UserLoginDto userLoginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));
        var tempUser = userRepository.getUserByEmail(userLoginDto.getEmail())
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
       userToSave.addRole(Role.ADD);
       userToSave.addRole(Role.UPDATE);

        userRepository.save(userToSave);
        var jwt = jwtService.generateToken(user);
        return ResponseEntity.ok().body(jwt);
    }
}
