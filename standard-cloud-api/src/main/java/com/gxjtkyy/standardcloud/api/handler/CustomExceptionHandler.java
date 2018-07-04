package com.gxjtkyy.standardcloud.api.handler;


import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * @Package com.gxjtkyy.quality.exception
 * @Author lizhenhua
 * @Date 2018/6/6 12:40
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVO Handle(Exception e){
        ResponseVO response = new ResponseVO();
        if (e instanceof BaseException){
            BaseException exception = (BaseException) e;
            response.invoke(exception);
        }else {
            //将系统异常以打印出来
            log.error("[系统异常]{}",e);

        }
        return response;
    }
}
