package com.gxjtkyy.standardcloud.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 视图配置器
 * @Package com.gxjtkyy.standardcloud.admin.config
 * @Author lizhenhua
 * @Date 2018/6/28 8:57
 */
@Configuration
public class ViewConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry){
//        registry.addViewController("/admin/index").setViewName("index");
//        registry.addViewController("/admin/template/index").setViewName("template");
//        registry.addViewController("/admin/doc/index").setViewName("doc");
//    }
}
