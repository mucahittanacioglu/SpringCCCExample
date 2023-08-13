package com.example.springtest.business.validation;


import com.example.springtest.entity.City;
import com.ts.core.exceptions.ValidationException;

import org.springframework.stereotype.Component;

@Component
public class CityValidator implements com.ts.core.validation.IValidator<City> {

    @Override
    public void validate(City target) throws ValidationException {
        City city = (City) target;

        // Validation code
        if (city.getName() == null || city.getName().length() < 3) {
            throw new ValidationException("City name cannot be null or less than 3!");
        }
    }
}

