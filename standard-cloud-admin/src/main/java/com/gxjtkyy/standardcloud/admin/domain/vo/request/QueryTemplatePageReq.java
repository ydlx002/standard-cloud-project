package com.gxjtkyy.standardcloud.admin.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.PageRequestVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 模板分页请求对象
 * @Package com.gxjtkyy.standardcloud.admin.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/27 12:18
 */
@Setter
@Getter
@ToString(callSuper = true)
public class QueryTemplatePageReq extends PageRequestVO{

    /**模板名称*/
    private String templateName;

    /**文档类型*/
    private Integer docType;


}
