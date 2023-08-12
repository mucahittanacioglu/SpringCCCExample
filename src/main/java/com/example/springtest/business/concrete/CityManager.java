package com.example.springtest.business.concrete;

import com.example.springtest.business.abstracts.ICityService;
import com.example.springtest.core.logging.Log;
import com.example.springtest.dataaccess.ICityDal;
import com.example.springtest.entity.City;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements ICityService {

    @Qualifier("cityCrudRepository")
    @Autowired
    private ICityDal cityDal;

    @Override
    @Transactional
    public List<City> getAll() {

        return this.cityDal.getAll();
    }

    @Override
    @Transactional
    public void add(City city) {
        this.cityDal.add(city);

    }

    @Override
    @Transactional
    public void update(City city) {
        this.cityDal.update(city);

    }

    @Override
    @Transactional
    public void delete(City city) {
        this.cityDal.delete(city);

    }

    @Override
    @Transactional
    public City getById(int id) {
        return this.cityDal.getById(id);
    }
    @Log
    @Override
    public void testServiceMethod(){
        System.out.println("Service worked!");
    }
}