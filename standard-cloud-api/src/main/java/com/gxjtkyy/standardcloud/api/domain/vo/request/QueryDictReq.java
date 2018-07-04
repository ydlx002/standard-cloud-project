package com.gxjtkyy.standardcloud.api.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字典值查询请求体
 * @Package com.gxjtkyy.standardcloud.api.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/30 11:41
 */
@Setter
@Getter
@ToString(callSuper = true)
public class QueryDictReq extends RequestVO{

    /**字典编码*/
    private String dictCode;

    /**字典名称  模糊查询*/
    private String dictName;


}
