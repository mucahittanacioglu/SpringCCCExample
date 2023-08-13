package com.example.springtest.business.concrete;

import com.example.springtest.business.abstracts.ICityService;

import com.example.springtest.business.validation.CityValidator;
import com.example.springtest.dataaccess.ICityDal;
import com.example.springtest.entity.City;
import com.ts.core.caching.CacheAction;
import com.ts.core.caching.Cacheable;
import com.ts.core.entities.Role;
import com.ts.core.logging.Log;
import com.ts.core.logging.LogAction;
import com.ts.core.security.RequiredRoles;
import com.ts.core.validation.Validate;
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
    @RequiredRoles({Role.READ})
    @Log(action = {LogAction.ERROR, LogAction.INFO})
    @Cacheable(cacheName = "first",key="ALL_CITIES",action = CacheAction.READ)
    public List<City> getAll() {
        return this.cityDal.getAll();
    }
    @Override
    @RequiredRoles({Role.ADD})
    @Validate(CityValidator.class)
    @Log(action = {LogAction.ERROR, LogAction.INFO,LogAction.DEBUG,LogAction.WARNING})
    public void add(City city) {
        this.cityDal.add(city);
    }
    @Override
    public void update(City city) {
        this.cityDal.update(city);
    }

    @Override
    public void delete(City city) {
        this.cityDal.delete(city);
    }

    @Override
//    @Cacheable(cacheName = "second",keyExpression="#id",action = CacheAction.READ)
    public City getById(int id) {
        return this.cityDal.getById(id);
    }

    @Override
    public void testServiceMethod(){
        System.out.println("Service worked!");
    }
}