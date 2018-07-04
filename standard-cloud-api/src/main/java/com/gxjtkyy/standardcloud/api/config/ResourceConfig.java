package com.gxjtkyy.standardcloud.api.config;

import com.gxjtkyy.standardcloud.common.constant.DocConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 静态资源配置
 * @Package com.gxjtkyy.standardcloud.api.config
 * @Author lizhenhua
 * @Date 2018/6/29 9:50
 */
@Configuration
public class ResourceConfig extends WebMvcConfigurerAdapter {

    @Value("${upload.path}")
    private String uploadPath;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(DocConstant.STATIC_RESOURCE_PATH +"/**").addResourceLocations("file:"+uploadPath+"/attach/");
        super.addResourceHandlers(registry);
    }
}
