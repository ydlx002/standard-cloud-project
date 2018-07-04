package com.gxjtkyy.standardcloud.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package com.gxjtkyy.standardcloud.common.annotation
 * @Author lizhenhua
 * @Date 2018/6/30 23:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogAction {
}
