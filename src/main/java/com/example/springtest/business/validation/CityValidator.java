package com.example.springtest.business.validation;

import com.example.springtest.core.entities.IEntity;
import com.example.springtest.core.exceptions.ValidationException;
import com.example.springtest.core.validation.IValidator;
import com.example.springtest.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityValidator implements IValidator<City> {

    @Override
    public void validate(City target) throws ValidationException {
        City city = (City) target;

        // Validation code
        if (city.getName() == null || city.getName().length() < 3) {
            throw new ValidationException("User name cannot be null or empty!");
        }
    }
}

