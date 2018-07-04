package com.gxjtkyy.standardcloud.admin.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.PageRequestVO;
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
public class QueryDictPageReq extends PageRequestVO{

    /**字典编码*/
    private String dictCode;

    /**字典名称*/
    private String dictName;

    /**字典类型*/
    private Integer dictType;


}
