package com.capgemini.consultant.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FirstNameValidator implements ConstraintValidator<FirstNameConstraint, String> {

    @Override
    public void initialize(FirstNameConstraint constraintAnnotation) {
        // This should be empty
    }

    @Override
    public boolean isValid(String firstName, ConstraintValidatorContext ctx) {
        return firstName != null && firstName.matches("[A-Za-z]+")
                && (firstName.length() > 3) && (firstName.length() < 20);
    }
}
