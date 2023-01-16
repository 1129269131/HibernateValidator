package com.koala.hibernatevalidator.nestedValidator;

/**
 * 嵌套对象校验
 * Create by koala on 2023-01-14
 */

import com.koala.hibernatevalidator.nestedValidator.entity.Employee;
import com.koala.hibernatevalidator.nestedValidator.entity.Org;
import com.koala.hibernatevalidator.nestedValidator.entity.Person;
import org.hibernate.validator.HibernateValidator;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NestedValidateTest {
    // 初始化一个校验器工厂
    private static ValidatorFactory validatorFactory = Validation
            .byProvider(HibernateValidator.class)
            .configure()
            // 校验失败是否立即返回： true-遇到一个错误立即返回不在往下校验，false-校验完所有字段才返回
            .failFast(false)
            .buildValidatorFactory();

    Validator validator = validatorFactory.getValidator();

    @Test
    public void test() {
        Person p = new Person();
        p.setAge(30);
        p.setName("zhangsan");
        //p.setIsMarried(true);

        Person p2 = new Person();
        p2.setAge(30);
        //p2.setName("zhangsan2");
        p2.setIsMarried(false);
        //p2.setHasChild(true);

        Org org = new Org();

        List<Person> list = new ArrayList<>();
        list.add(p);
        list.add(p2);
        //增加一个null，测试是否会校验元素为null
        list.add(null);

        Employee e = new Employee();
        e.setPeople(list);
        org.setEmployee(e);

        Set<ConstraintViolation<Org>> result = validator.validate(org);

        System.out.println("遍历输出错误信息：");
        result.forEach(r -> System.out.println(r.getPropertyPath() + ":" + r.getMessage()));
    }

}