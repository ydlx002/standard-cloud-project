package com.gxjtkyy.standardcloud.api.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.DocRequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 获取分类
 * @Package com.gxjtkyy.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/19 18:37
 */
@Setter
@Getter
@ToString(callSuper = true)
public class QueryCategoryReq extends DocRequestVO {

    /**一级分类*/
    private String topCategory;

    /**二级分类*/
    private String subCategory;

    /**三级分类*/
    private String thirdCategory;

}
