package com.koala.hibernatevalidator.nestedValidator.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Create by koala on 2023-01-14
 */
@Data
public class Employee {

    /**
     * 此处用到容器元素级别的约束: List<@Valid @NotNull Person>
     * 会校验容器内部元素是否为null，否则为null时会跳过校验
     * NotNull注解的target包含ElementType.TYPE_USE，因此NotNull可以给泛型注解
     */
    @Valid
    @NotNull(message = "person不能为空")
    private List<@Valid @NotNull Person> people;
}