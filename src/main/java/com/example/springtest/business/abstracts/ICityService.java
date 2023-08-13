package com.example.springtest.business.abstracts;

import com.example.springtest.entity.City;


import java.util.List;
import java.util.Optional;

public interface ICityService {
    List<City> getAll();
    void add(City city);
    void update(City city);
    void delete(City city);
    City getById(int id);
    void testServiceMethod();
}
