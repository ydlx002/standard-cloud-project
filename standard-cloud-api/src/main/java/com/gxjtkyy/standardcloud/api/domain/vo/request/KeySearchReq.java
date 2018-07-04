package com.gxjtkyy.standardcloud.api.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.PageRequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import com.gxjtkyy.standardcloud.common.validation.annotation.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 根据关键字搜索文档
 * @Package com.gxjtkyy.standardcloud.api.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/30 11:36
 */
@Setter
@Getter
@ToString(callSuper = true)
public class KeySearchReq extends PageRequestVO{

    /**关键字*/
    @NotEmpty
    private String key;


}
