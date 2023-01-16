package com.koala.hibernatevalidator.customValidator;

import com.koala.hibernatevalidator.customValidator.entity.Person;
import org.hibernate.validator.HibernateValidator;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 自定义校验
 * Create by koala on 2023-01-14
 */
public class CustomValidator {
    // 初始化一个校验器工厂
    private static ValidatorFactory validatorFactory = Validation
            .byProvider(HibernateValidator.class)
            .configure()
            // 校验失败是否立即返回： true-遇到一个错误立即返回不在往下校验，false-校验完所有字段才返回
            .failFast(false)
            .buildValidatorFactory();

    Validator validator = validatorFactory.getValidator();

    /**
     * 自定义手机号码校验器
     */
    @Test
    public void test() {
        Person person = new Person();
        person.setTelePhone("888888");

        Set<ConstraintViolation<Person>> result = validator.validate(person);

        System.out.println("遍历输出错误信息:");
        // getPropertyPath() 获取属性全路径名
        // getMessage() 获取校验后的错误提示信息
        result.forEach(r -> System.out.println(r.getPropertyPath() + ":" + r.getMessage()));
    }
}