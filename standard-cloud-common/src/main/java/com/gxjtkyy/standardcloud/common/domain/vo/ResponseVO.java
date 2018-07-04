package com.gxjtkyy.standardcloud.common.domain.vo;


import com.gxjtkyy.standardcloud.common.constant.ResultCode;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 响应基础类
 * @Package com.gxjtkyy.domain.vo
 * @Author lizhenhua
 * @Date 2018/5/29 8:53
 */
@Setter
@Getter
@ToString
public class ResponseVO implements Serializable{

    /**请求流水号*/
    private String streamNo;

    /**平台*/
    private String platForm;

    public ResponseVO(){
        code = ResultCode.RESULT_CODE_0000;
        msg = ResultCode.RESULT_DESC_0000;
    }

    /**响应码*/
    private String code;

    /**响应描述*/
    private String msg;

    /**响应数据*/
    private Object data;


    public void invoke(BaseException ex){
        this.setCode(ex.getCode());
        this.setMsg(ex.getMsg());
    }

}
