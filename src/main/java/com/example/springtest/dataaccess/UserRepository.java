package com.example.springtest.dataaccess;

import com.example.springtest.entity.User;

import com.ts.core.repository.IUserRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Repository
public interface UserRepository extends IUserRepository<User>,JpaRepository<User,Long>{
    @Override
    Optional<User> getUserByEmail(String email);

}
