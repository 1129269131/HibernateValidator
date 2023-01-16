package com.koala.hibernatevalidator.groupValidator.entity;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by koala on 2023-01-14
 */
public class PeopleGroupSequenceProvider implements DefaultGroupSequenceProvider<People2> {
    @Override
    public List<Class<?>> getValidationGroups(People2 bean) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 这里必须将校验对象的类加进来,否则没有Default分组会抛异常，这个地方还没太弄明白，后面有时间再研究一下
        defaultGroupSequence.add(People2.class);

        if (bean != null) {
            Boolean isMarried = bean.getIsMarried();
            ///System.err.println("是否已婚：" + isMarried + "，执行对应校验逻辑");
            if (isMarried != null) {
                if (isMarried) {
                    System.err.println("是否已婚：" + isMarried + "，groups: " + Group.Married.class);
                    defaultGroupSequence.add(Group.Married.class);
                } else {
                    System.err.println("是否已婚：" + isMarried + "，groups: " + Group.UnMarried.class);
                    defaultGroupSequence.add(Group.UnMarried.class);
                }
            } else {
                System.err.println("isMarried is null");
                defaultGroupSequence.add(Group.Married.class);
                defaultGroupSequence.add(Group.UnMarried.class);
            }
        } else {
            System.err.println("bean is null");
        }

        return defaultGroupSequence;
    }
}