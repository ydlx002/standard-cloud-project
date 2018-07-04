package com.gxjtkyy.standardcloud.common.domain.vo;

import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 分页请求基础类
 * @Package com.gxjtkyy.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/21 9:00
 */
@Setter
@Getter
@ToString(callSuper = true)
public class PageRequestVO extends RequestVO {

    /**当前页*/
    private int currentPage = 1;

    /**页大小*/
    private int pageSize = 10;
}
