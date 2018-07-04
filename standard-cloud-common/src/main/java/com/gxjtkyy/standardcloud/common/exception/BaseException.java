package com.gxjtkyy.standardcloud.common.exception;

import lombok.Getter;

/**
 * 基础异常类
 * @Package com.gxjtkyy.exception
 * @Author lizhenhua
 * @Date 2018/5/24 21:42
 */
@Getter
public class BaseException extends Exception{

    protected String code;

    protected String msg;

    public BaseException(){
        super();
    }

    public BaseException(String message){
        super(message);
    }

    public BaseException(String message, Throwable cause){
        super(message, cause);
    }

    public BaseException(Throwable cause){
        super( cause);
    }

    @Override
    public String toString(){
        return "message:"+this.getMsg() +" ("+this.code+"["+this.getMsg()+"])";
    }
}
