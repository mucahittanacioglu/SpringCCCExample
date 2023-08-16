package com.example.springtest.business.abstracts;

import com.example.springtest.business.dtos.UserLoginDto;
import com.example.springtest.business.dtos.UserRegisterDto;
import org.springframework.http.ResponseEntity;

public interface IAuthService {

    ResponseEntity<String> signIn(UserLoginDto userLoginDto) throws Throwable;
    ResponseEntity<String> signUp(UserRegisterDto userRegisterDto);

}
