package com.koala.hibernatevalidator.nestedValidator.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Create by koala on 2023-01-14
 */
@Data
public class Person {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Range(min = 0, max = 100, message = "年龄必须在{min}和{max}之间")
    private Integer age;

    @NotNull(message = "是否已婚不能为空")
    private Boolean isMarried;

    @NotNull(message = "是否有小孩不能为空")
    private Boolean hasChild;

    @NotNull(message = "小孩个数不能为空")
    private Integer childCount;

    @NotNull(message = "是否单身不能为空")
    private Boolean isSingle;

}