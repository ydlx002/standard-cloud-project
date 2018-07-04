package com.gxjtkyy.standardcloud.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextUtil
 * @Package com.gxjtkyy.utils
 * @Author lizhenhua
 * @Date 2018/6/11 15:53
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {


    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    /**
     * 获取对象
     * 这里重写了bean方法，起主要作用
     * @param clazz
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    public static <T> T getBean(Class clazz) throws BeansException {
        return (T)applicationContext.getBean(clazz);
    }


    /**
     * 获取对象
     * 这里重写了bean方法，起主要作用
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    public static <T> T getBean(String name) throws BeansException {
        return (T)applicationContext.getBean(name);
    }
}
