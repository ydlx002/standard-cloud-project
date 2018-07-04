package com.gxjtkyy.standardcloud.common.exception;


import com.gxjtkyy.standardcloud.common.constant.ResultCode;

/**
 * 模板异常
 * @Package com.gxjtkyy.exception
 * @Author lizhenhua
 * @Date 2018/5/24 16:33
 */
public class TemplateException extends BaseException{

    public TemplateException(){
        super();
    }

    public TemplateException(String resultCode){
        super(resultCode);
        this.code = resultCode;
        this.msg = ResultCode.getDesc(resultCode);
    }

    public TemplateException(String resultCode, String resultDesc){
        super(resultDesc);
        this.code = resultCode;
        this.msg = resultDesc;
    }

    public TemplateException(String message, Throwable cause){
        super(message, cause);
    }

    public TemplateException(Throwable cause){
        super( cause);
    }
}
