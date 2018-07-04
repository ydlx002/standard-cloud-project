package com.gxjtkyy.standardcloud.common.exception;

/**
 * 文档异常
 * @Package com.gxjtkyy.exception
 * @Author lizhenhua
 * @Date 2018/5/24 16:33
 */
public class DocException extends BaseException{

    public DocException(){
        super();
    }

    public DocException(String message){
        super(message);
    }

    public DocException(String message, Throwable cause){
        super(message, cause);
    }

    public DocException(Throwable cause){
        super( cause);
    }

    public DocException(String resultCode, String resultDesc){
        super(resultDesc);
        this.code = resultCode;
        this.msg = resultDesc;
    }
}
