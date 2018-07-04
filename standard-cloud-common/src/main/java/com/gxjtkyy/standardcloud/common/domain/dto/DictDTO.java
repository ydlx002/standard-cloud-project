package com.gxjtkyy.standardcloud.common.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 字典数据兑对象
 * @Package com.gxjtkyy.domain.dto
 * @Author lizhenhua
 * @Date 2018/5/24 11:22
 */
@Setter
@Getter
@ToString(callSuper = true)
public class DictDTO extends BaseDTO{

    /**序号*/
    private Integer id;

    /**字典码*/
    private String dictCode;

    /**字典类型*/
    private Integer dictType;

    /**字典名称*/
    private String dictName;

    /**字典描述*/
    private String dictDesc;

    /**操作员*/
    private String operator;
    
    /**操作员ID*/
    private String operatorId;


}
