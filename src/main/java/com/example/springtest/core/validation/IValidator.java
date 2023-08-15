package com.example.springtest.core.validation;

import com.example.springtest.core.entities.IEntity;
import com.example.springtest.core.exceptions.ValidationException;

public interface IValidator<T extends IEntity> {
    void validate(T entity) throws ValidationException;

}