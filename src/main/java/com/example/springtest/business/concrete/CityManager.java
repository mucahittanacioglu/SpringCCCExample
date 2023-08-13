package com.example.springtest.business.concrete;

import com.example.springtest.business.abstracts.ICityService;
import com.example.springtest.business.validation.CityValidator;
import com.example.springtest.core.caching.CacheAction;
import com.example.springtest.core.caching.Cacheable;
import com.example.springtest.core.entities.Role;
import com.example.springtest.core.logging.Log;
import com.example.springtest.core.logging.LogAction;
import com.example.springtest.core.security.RequiredRoles;
import com.example.springtest.core.validation.Validate;
import com.example.springtest.dataaccess.ICityDal;
import com.example.springtest.entity.City;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    @Cacheable(cacheName = "first",key="ALL_CITIES",action = CacheAction.READ)
    @Log(action = {LogAction.ERROR, LogAction.INFO})
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
    @Cacheable(cacheName = "second",keyExpression="#id",action = CacheAction.READ)
    public City getById(int id) {
        return this.cityDal.getById(id);
    }

    @Override
    public void testServiceMethod(){
        System.out.println("Service worked!");
    }
}