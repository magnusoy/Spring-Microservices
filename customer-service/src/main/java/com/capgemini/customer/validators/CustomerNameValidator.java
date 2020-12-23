package com.capgemini.customer.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerNameValidator implements ConstraintValidator<CustomerNameConstraint, String> {

    @Override
    public void initialize(CustomerNameConstraint constraintAnnotation) {
        // This should be empty
    }

    @Override
    public boolean isValid(String customerName, ConstraintValidatorContext ctx) {
        return customerName != null && customerName.matches("[A-Za-z]+")
                && (customerName.length() > 1) && (customerName.length() < 20);
    }
}
