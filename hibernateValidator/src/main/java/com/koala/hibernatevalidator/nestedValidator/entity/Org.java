package com.koala.hibernatevalidator.nestedValidator.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Create by koala on 2023-01-14
 */
@Data
public class Org {

    @Valid  //如果此处不用Valid注解，则不会去校验Employee对象的内部字段
    @NotNull(message = "employee不能为空")
    private Employee employee;

}