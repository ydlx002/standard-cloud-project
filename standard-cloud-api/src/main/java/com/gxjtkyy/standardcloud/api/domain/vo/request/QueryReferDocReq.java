package com.gxjtkyy.standardcloud.api.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.PageRequestVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 查询相关产品请求体
 * @Package com.gxjtkyy.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/26 10:19
 */
@Setter
@Getter
@ToString(callSuper = true)
public class QueryReferDocReq extends PageRequestVO {

    /**docId*/
    private String docId;

    /**方法标准编号*/
    private String deteBasis;


}
