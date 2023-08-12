package com.example.springtest.core.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "roles")@Getter
public enum Role {
    ADD,DELETE,UPDATE;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;


}
