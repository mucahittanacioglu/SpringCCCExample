package com.example.springtest.entity;

import com.example.springtest.core.entities.IEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name="city")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class City extends IEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="name")
    private String name;

    @Column(name="countrycode")
    private String countryCode;

    @Column(name="district")
    private String district;

    @Column(name="population")
    private int population;


}
