package com.gxjtkyy.standardcloud.api.config;

import com.gxjtkyy.standardcloud.common.aop.ApiAspect;
import com.gxjtkyy.standardcloud.common.aop.LogAspect;
import com.gxjtkyy.standardcloud.common.aop.ValidateAspect;
import com.gxjtkyy.standardcloud.common.utils.ApplicationContextUtil;
import com.gxjtkyy.standardcloud.common.validation.ParamValidator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @Package com.gxjtkyy.standardcloud.api.config
 * @Author lizhenhua
 * @Date 2018/6/27 11:28
 */
@Configuration
@MapperScan("com.gxjtkyy.standardcloud.*.dao")
public class BeanConfig {

    @Bean
    ApplicationContextUtil applicationContextUtil(){
        return new ApplicationContextUtil();
    }


    /**-----------------------------> paramValidator  start<---------------------------------------------*/

    @Bean
    public ApiAspect apiAspect(){
        return new ApiAspect();
    }

    @Bean
    public ParamValidator paramValidator(){
        return new ParamValidator(localValidatorFactoryBean());
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(){
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setProviderClass(org.hibernate.validator.HibernateValidator.class);
        return  localValidatorFactoryBean;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
    /**-----------------------------> paramValidator  end<---------------------------------------------*/


}
