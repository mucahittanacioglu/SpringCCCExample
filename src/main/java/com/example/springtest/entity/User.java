package com.example.springtest.entity;

import com.ts.core.entities.IUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")@Setter@Getter@AllArgsConstructor@
        NoArgsConstructor
public class User extends IUser {
    @Column(name = "name")
    private String name;
}
