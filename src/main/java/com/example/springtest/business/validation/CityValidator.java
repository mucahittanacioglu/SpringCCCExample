package com.example.springtest.business.validation;
import com.ts.core.exceptions.ValidationException;
import com.ts.core.validation.IValidator;
import com.example.springtest.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityValidator implements IValidator<City> {

    @Override
    public void validate(City city) throws ValidationException {
        // Validation code
        if (city.getName() == null || city.getName().length() < 3) {
            throw new ValidationException("User name cannot be null or empty!");
        }
    }
}

