package com.gusia.backend.validators;

import com.gusia.backend.exceptions.ObjectNotValidException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;

public class ObjectValidator<T> {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validate(T obj) {
        Set<ConstraintViolation<T>> violations = validator.validate(obj);
        if (!violations.isEmpty()) {
            List<String> errorMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();
            throw new ObjectNotValidException(errorMessages);
        }
    }
}
