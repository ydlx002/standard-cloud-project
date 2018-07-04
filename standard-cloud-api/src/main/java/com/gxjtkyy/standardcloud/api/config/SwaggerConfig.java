package com.gxjtkyy.standardcloud.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger文档配置
 * @Package com.gxjtkyy.config
 * @Author lizhenhua
 * @Date 2018/5/21 16:29
 */
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo( new ApiInfoBuilder()
                .title("标准云API")
                .description("标准云项目后台API")
                .termsOfServiceUrl("http://192.168.31.54:8080")
                .version("1.0")
                .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gxjtkyy.standardcloud.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
