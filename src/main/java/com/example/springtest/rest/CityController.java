package com.example.springtest.rest;



import com.example.springtest.business.abstracts.ICityService;

import com.example.springtest.entity.City;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@AllArgsConstructor
@RequiredArgsConstructor
public class CityController {
    @Autowired
    private ICityService cityService;
    @GetMapping("/cities")
    public List<City> get(){
        return cityService.getAll();
    }

    @PostMapping("/add")
    public void add(@RequestBody City city) {
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