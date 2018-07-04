package com.gxjtkyy.standardcloud.api.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.PageRequestVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 分页请求检测方法对象
 * @Package com.gxjtkyy.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/6/21 9:05
 */
@Setter
@Getter
@ToString(callSuper = true)
public class QueryDeteMthPageReq extends PageRequestVO {

    /**docId*/
    private String docId;
    

}
