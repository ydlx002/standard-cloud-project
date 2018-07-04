package com.gxjtkyy.standardcloud.common.domain.vo;

import com.gxjtkyy.standardcloud.common.validation.annotation.EnumType;
import com.gxjtkyy.standardcloud.common.validation.annotation.NotEmpty;
import com.gxjtkyy.standardcloud.common.validation.annotation.Regex;
import com.gxjtkyy.standardcloud.common.validation.annotation.StringLength;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 请求实体
 * @Package com.gxjtkyy.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/16 16:40
 */
@Setter
@Getter
@ToString
public class RequestVO implements Serializable{
    
    /**请求流水号*/
    @NotEmpty
    @StringLength(min = 6, max = 64)
    @Regex("^\\w+$")
    private String streamNo;

    /**平台*/
    @NotEmpty
    @EnumType(enums = {"web","app"})
    private String platForm;



}
