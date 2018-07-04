package com.gxjtkyy.standardcloud.api.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.DocRequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 查询检测方法
 * @Package com.gxjtkyy.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/20 8:44
 */
@Setter
@Getter
@ToString(callSuper = true)
public class QueryDeteMthReq extends DocRequestVO {

    /**文档类型*/
    private Integer docType;

    /**检测方法名*/
    private String deteMth;

    /**检测项*/
    private String deteItem;

    /**检测依据*/
    private String deteBasis;


}
