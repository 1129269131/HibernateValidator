package com.koala.hibernatevalidator.baseValidator;

import com.koala.hibernatevalidator.baseValidator.entity.Person;
import org.hibernate.validator.HibernateValidator;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

/**
 * 校验的基本使用
 * Create by koala on 2023-01-12
 */
public class ValidateTest {
    // 初始化一个校验器工厂
    private static ValidatorFactory validatorFactory = Validation
            .byProvider(HibernateValidator.class)
            .configure()
            // 校验失败是否立即返回： true-遇到一个错误立即返回不在往下校验，false-校验完所有字段才返回
            .failFast(false)
            .buildValidatorFactory();

    Validator validator = validatorFactory.getValidator();

    /**
     * 简单对象校验
     */
    @Test
    public void testSimple() {
        Person person = new Person();
        person.setValue("value");
        person.setName(" ");
        person.setAge(101);
        person.setEmail("email");

        person.setIsAssertTrue(Boolean.FALSE);
        person.setIsAssertFalse(Boolean.TRUE);

        person.setFutureDate(new Date());
        person.setFutureOrPresentDate(new Date());
        person.setPastDate(new Date(2022, 1, 1));
        person.setPastOrPresentDate(new Date(2022, 1, 1));

        person.setMax(10);
        person.setMin(1);

        person.setDecimalMax(new BigDecimal("1.2"));
        person.setDecimalMin(new BigDecimal("1.0"));

        person.setNegative(1);
        person.setNegativeOrZero(1);

        person.setPositive(-1);
        person.setPositiveOrZero(-1);

        person.setDigits(new BigDecimal(100.1));

        person.setPattern("abc");

        person.setSize(Arrays.asList("a", "b", "c"));

        person.setLength("a");

        person.setUrl("aaa");

        Set<ConstraintViolation<Person>> result = validator.validate(person);

        System.out.println("遍历输出错误信息:");
        // getPropertyPath() 获取属性全路径名
        // getMessage() 获取校验后的错误提示信息
        result.forEach(r -> System.out.println(r.getPropertyPath() + ":" + r.getMessage()));
    }
}