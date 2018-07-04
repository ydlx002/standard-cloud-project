package com.gxjtkyy.standardcloud.common.domain.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 文档请求类
 * @Package com.gxjtkyy.standardcloud.common.domain.vo
 * @Author lizhenhua
 * @Date 2018/6/30 11:30
 */
@Setter
@Getter
@ToString(callSuper = true)
public class DocRequestVO extends RequestVO{
    
    /**文档ID*/
    private String docId;


}
