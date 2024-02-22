package com.emp.management.util.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Date;
import java.util.regex.Pattern;

public class DateValidator implements ConstraintValidator<ValidateDate, Date> {
    @Override
    public void initialize(ValidateDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null
                && Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", value.toString())
                && value.before(new Date());
    }
}