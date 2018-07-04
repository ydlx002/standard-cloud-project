package com.gxjtkyy.standardcloud.common.validation.annotation;


import com.gxjtkyy.standardcloud.common.validation.constraints.StringLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package io.github.ydlx.annotation
 * @Author lizhenhua
 * @Date 2018/7/4 10:47
 */
@Target(ElementType.FIELD)//作用于的类型，此处为对象的属性
@Retention(RetentionPolicy.RUNTIME)//运行时生效
@Constraint(validatedBy = StringLengthValidator.class)//通过PasswordNotNullValidator类实现注解的相关校验操作
public @interface StringLength {

    long min() default 0;

    long max() default Long.MAX_VALUE;

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
