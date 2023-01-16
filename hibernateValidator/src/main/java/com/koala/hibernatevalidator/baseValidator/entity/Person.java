package com.koala.hibernatevalidator.baseValidator.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * Create by koala on 2023-01-12
 */
@Data
public class Person {

    @Null(message = "值只能为空")
    private String value;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotNull(message = "是否已婚不能为空")
    private Boolean isMarried;
    @NotEmpty(message = "家庭成员不能为空")
    private Collection collection;
    @NotEmpty(message = "个人学历不能为空")
    private String[] array;

    @AssertTrue(message = "AssertTrue")
    private Boolean isAssertTrue;
    @AssertFalse(message = "AssertFalse")
    private Boolean isAssertFalse;

    @Future(message = "futureDate")
    private Date futureDate;
    @FutureOrPresent(message = "futureOrPresentDate")
    private Date futureOrPresentDate;
    @Past(message = "pastDate")
    private Date pastDate;
    @PastOrPresent(message = "pastOrPresentDate")
    private Date pastOrPresentDate;

    @Max(value = 1, message = "max")
    private Integer max;
    @Min(value = 10, message = "min")
    private Integer min;

    @DecimalMax(value = "1.1", message = "decimalMax")
    private BigDecimal decimalMax;
    @DecimalMin(value = "1.1", message = "decimalMin")
    private BigDecimal decimalMin;

    @Negative(message = "negative")
    private Integer negative;
    @NegativeOrZero(message = "negativeOrZero")
    private Integer negativeOrZero;

    @Positive(message = "positive")
    private Integer positive;
    @PositiveOrZero(message = "positiveOrZero")
    private Integer positiveOrZero;

    @Digits(integer = 1, fraction = 1, message = "digits")
    private BigDecimal digits;

    @Pattern(regexp = "\\[0-9]+$", message = "pattern")
    private String pattern;

    @Email
    private String email;

    @Size(min = 10, max = 10, message = "size")
    private Collection size;

    @Length(min = 10, max = 10, message = "length")
    private String length;

    @Range(min = 0, max = 100, message = "年龄必须在{min}和{max}之间")
    private Integer age;

    @URL(message = "url")
    private String url;

}