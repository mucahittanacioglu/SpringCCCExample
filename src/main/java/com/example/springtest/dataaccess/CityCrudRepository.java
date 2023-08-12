package com.example.springtest.dataaccess;

import com.example.springtest.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class CityCrudRepository implements ICityDal{
     @Autowired
    ICityCrudRepository _crudRepo;

    public CityCrudRepository(ICityCrudRepository _crudRepo) {
        this._crudRepo = _crudRepo;
    }

    @Override
    public List<City> getAll() {
        List<City> result = new ArrayList<>();
        this._crudRepo.findAll().forEach(c->result.add(c));
        return result;
    }

    @Override
    public void add(City city) {
        this._crudRepo.save(city);
    }

    @Override
    public void update(City city) {
        this._crudRepo.save(city);
    }

    @Override
    public void delete(City city) {
        this._crudRepo.delete(city);
    }

    @Override
    public City getById(int id) {
        return this._crudRepo.findById(id).get();
    }
}
