package com.koala.hibernatevalidator.customValidator.entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Create by koala on 2023-01-14
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    /**
     * 手机验证规则
     */
    private Pattern pattern;

    @Override
    public void initialize(Mobile mobile) {
        pattern = Pattern.compile(mobile.regexp());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return pattern.matcher(value).matches();
    }
}