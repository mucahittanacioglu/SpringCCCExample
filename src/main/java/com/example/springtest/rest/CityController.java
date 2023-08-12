package com.example.springtest.rest;

import com.example.springtest.business.validation.CityValidator;
import com.example.springtest.entity.City;
import com.example.springtest.business.abstracts.ICityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {
    @Autowired
    private ICityService cityService;
    @Autowired
    private CityValidator cityValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.addValidators(cityValidator);
    }


    @GetMapping("/cities")
    public List<City> get(){
        return cityService.getAll();
    }

    @PostMapping("/add")
    public void add(@Valid @RequestBody City city) {
        cityService.add(city);
    }

    @PostMapping("/update")
    public void update(@RequestBody City city) {
        cityService.update(city);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody City city) {
        cityService.delete(city);
    }

    @GetMapping("/cities/{id}")
    public City getById(@PathVariable int id){
        return cityService.getById(id);
    }

}