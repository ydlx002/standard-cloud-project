package com.gxjtkyy.standardcloud.common.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Map;

/**
 * API日志存储类
 * @Package com.gxjtkyy.standardcloud.common.domain.dto
 * @Author lizhenhua
 * @Date 2018/7/3 15:23
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ApiLogDTO extends BaseDTO{

    /**id*/
    @Id
    private String logIndex;

    /**requesturi*/
    private String requestURI;
    
    /**ip*/
    private String ip;

    /**操作员*/
    private String operator;

    /**操作员ID*/
    private String operatorId;

    /**请求*/
    private Map request;

    /**响应*/
    private Map response;

}
