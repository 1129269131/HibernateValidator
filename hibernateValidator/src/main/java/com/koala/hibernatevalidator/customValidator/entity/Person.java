package com.koala.hibernatevalidator.customValidator.entity;

import lombok.Data;

/**
 * Create by koala on 2023-01-14
 */
@Data
public class Person {

    @Mobile(message = "telePhone")
    private String telePhone;
}