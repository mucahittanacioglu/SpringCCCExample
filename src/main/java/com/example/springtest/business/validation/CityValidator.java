package com.example.springtest.business.validation;

import com.example.springtest.entity.City;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CityValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return City.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        City city = (City) target;
        if(city.getName() == null || city.getName().length()<3){
            errors.rejectValue("name","Name can not be empty.");
        }
    }
}
