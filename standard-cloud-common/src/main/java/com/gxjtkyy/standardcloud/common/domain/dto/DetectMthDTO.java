package com.gxjtkyy.standardcloud.common.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 检测方法实体类
 * @Package com.gxjtkyy.domain.dto
 * @Author lizhenhua
 * @Date 2018/5/24 10:19
 */
@Setter
@Getter
@ToString
public class DetectMthDTO extends BaseDTO{

    /**方法id*/
    private String mthId;

    /**0 标准方法  1非标准方法*/
    private Integer mthType;

    /**方法名称*/
    private String mthName;

    /**检测方法内容*/
    private Object mthContent;

}
