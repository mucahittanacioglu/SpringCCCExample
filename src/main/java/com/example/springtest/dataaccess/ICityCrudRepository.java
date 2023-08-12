package com.example.springtest.dataaccess;

import com.example.springtest.entity.City;
import org.springframework.data.repository.CrudRepository;



public interface ICityCrudRepository extends CrudRepository<City, Integer> {


}
