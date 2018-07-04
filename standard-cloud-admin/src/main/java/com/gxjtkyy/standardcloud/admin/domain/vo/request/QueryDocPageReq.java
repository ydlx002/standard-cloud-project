package com.gxjtkyy.standardcloud.admin.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.PageRequestVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 分页查询请求体
 * @Package com.gxjtkyy.standardcloud.admin.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/28 8:20
 */
@Setter
@Getter
@ToString(callSuper = true)
public class QueryDocPageReq extends PageRequestVO{

    /**id*/
    private String docId;

    /**文档名称*/
    private String docName;

    /**文档类型*/
    private Integer docType;


}
