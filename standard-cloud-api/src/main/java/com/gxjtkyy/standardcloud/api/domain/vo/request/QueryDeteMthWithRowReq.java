package com.gxjtkyy.standardcloud.api.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.DocRequestVO;
import com.gxjtkyy.standardcloud.common.validation.annotation.NotNull;
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
public class QueryDeteMthWithRowReq extends DocRequestVO {

    /**文档类型*/
    @NotNull
    private Integer rowId;


}
