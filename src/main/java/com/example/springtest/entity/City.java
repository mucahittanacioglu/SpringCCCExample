package com.example.springtest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;



@Entity
@Table(name="city")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class City {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="countrycode")
    private String countryCode;

    @Column(name="district")
    private String district;

    @Column(name="population")
    private int population;


}