package com.gxjtkyy.standardcloud.admin.domain.vo;

import com.gxjtkyy.standardcloud.common.domain.vo.BaseVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Package com.gxjtkyy.standardcloud.admin.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/27 13:00
 */
@Setter
@Getter
@ToString(callSuper = true)
public class TemplateVO extends BaseVO{

    /**id*/
    private String id;

    /**模板名称*/
    private String templateName;

    /**文档类型*/
    private Integer docType;
    
    /**模板描述*/
    private String templateDesc;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;


}
