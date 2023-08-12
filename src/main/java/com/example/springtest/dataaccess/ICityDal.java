package com.example.springtest.dataaccess;

import com.example.springtest.entity.City;

import java.util.List;

public interface ICityDal {
    List<City> getAll();
    void add(City city);
    void update(City city);
    void delete(City city);
    City getById(int id);
}
