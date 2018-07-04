package com.gxjtkyy.standardcloud.common.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 文档基础类
 * @Package com.gxjtkyy.domain.dto
 * @Author lizhenhua
 * @Date 2018/5/28 14:45
 */
@Setter
@Getter
@ToString(callSuper = true)
public class BaseDocDTO extends BaseDTO{

    /**标准文档ID*/
    @Id
    private String docId;

    /**文档名称，在此指代产品标准名*/
    private String docName;

    /**文档类型 0 标准文档 1方法标准 2 抽样细则 3判定标准*/
    private Integer docType;

    /**指定模板ID*/
    private String templateId;


}
