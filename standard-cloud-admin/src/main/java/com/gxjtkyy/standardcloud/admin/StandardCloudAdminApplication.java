package com.gxjtkyy.standardcloud.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Package com.gxjtkyy.standardcloud.admin
 * @Author lizhenhua
 * @Date 2018/6/27 13:15
 */
@SpringBootApplication
@EnableSwagger2
public class StandardCloudAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(StandardCloudAdminApplication.class, args);
    }
}
