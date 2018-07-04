package com.gxjtkyy.standardcloud.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * API操作
 * @Package com.gxjtkyy.standardcloud.common.annotation
 * @Author lizhenhua
 * @Date 2018/7/3 14:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiAction {

    String value() default "";

    /**是否记录日志*/
    boolean saveLog() default false;

    /**是否进行认证*/
    boolean auth() default false;
}
