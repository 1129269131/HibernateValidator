package com.koala.hibernatevalidator.groupValidator;

import com.koala.hibernatevalidator.groupValidator.entity.Group;
import com.koala.hibernatevalidator.groupValidator.entity.People;
import com.koala.hibernatevalidator.groupValidator.entity.People2;
import org.hibernate.validator.HibernateValidator;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 动态分组校验
 * Create by koala on 2023-01-14
 */
public class GroupValidateTest {
    // 初始化一个校验器工厂
    private static ValidatorFactory validatorFactory = Validation
            .byProvider(HibernateValidator.class)
            .configure()
            // 校验失败是否立即返回： true-遇到一个错误立即返回不在往下校验，false-校验完所有字段才返回
            .failFast(false)
            .buildValidatorFactory();

    Validator validator = validatorFactory.getValidator();

    /**
     * 动态分组校验
     */
    @Test
    public void testGroup() {
        People p = new People();
        p.setAge(30);
        p.setName(" ");
        p.setIsMarried(false);

        Set<ConstraintViolation<People>> result;
        //通过isMarried的值来动态指定分组校验
        if (p.getIsMarried()) {
            //如果已婚，则按照已婚的分组字段
            result = validator.validate(p, Group.Married.class);
        } else {
            //如果未婚，则只校验未婚的分组字段
            result = validator.validate(p, Group.UnMarried.class);
        }

        System.out.println("遍历输出错误信息：");
        result.forEach(r -> System.out.println(r.getPropertyPath() + ":" + r.getMessage()));
    }

    /**
     * 动态分组校验优化
     */
    @Test
    public void testGroupSequence() {
        People2 p = new People2();
        p.setAge(30);
        p.setName(" ");

        System.out.println("----已婚情况:");
        p.setIsMarried(true);
        Set<ConstraintViolation<People2>> result = validator.validate(p);
        System.out.println("遍历输出错误信息：");
        result.forEach(r -> System.out.println(r.getPropertyPath() + ":" + r.getMessage()));

        System.out.println("----未婚情况:");
        p.setIsMarried(false);
        result = validator.validate(p);
        System.out.println("遍历输出错误信息：");
        result.forEach(r -> System.out.println(r.getPropertyPath() + ":" + r.getMessage()));
    }
}