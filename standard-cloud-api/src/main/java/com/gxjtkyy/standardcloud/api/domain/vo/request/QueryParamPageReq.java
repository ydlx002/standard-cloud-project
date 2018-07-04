package com.gxjtkyy.standardcloud.api.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.PageRequestVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 参数查询请求
 * @Package com.gxjtkyy.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/16 16:42
 */
@Setter
@Getter
@ToString(callSuper = true)
public class QueryParamPageReq extends PageRequestVO {

    /**文档ID*/
    private String docId;

    /**一级分类*/
    private String topCategory;

    /**二级分类*/
    private String subCategory;
    
    /**三级分类*/
    private String thirdCategory;


}
