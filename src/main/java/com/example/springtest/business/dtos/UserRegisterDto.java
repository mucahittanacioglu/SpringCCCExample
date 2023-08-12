package com.example.springtest.business.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    private String name;

    private String email;

    private String password;
}
