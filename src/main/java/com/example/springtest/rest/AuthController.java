package com.example.springtest.rest;

import com.example.springtest.business.abstracts.IAuthService;
import com.example.springtest.business.dtos.UserLoginDto;
import com.example.springtest.business.dtos.UserRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserRegisterDto userRegisterDto) {
        return authService.signUp(userRegisterDto);
    }
    @GetMapping("/signIn")
    public ResponseEntity<String> signUp(@RequestBody UserLoginDto userLoginDto) {
        return authService.signIn(userLoginDto);
    }
}
