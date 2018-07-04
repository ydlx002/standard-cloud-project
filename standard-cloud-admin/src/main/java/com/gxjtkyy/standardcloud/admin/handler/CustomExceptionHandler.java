package com.gxjtkyy.standardcloud.admin.handler;


import com.gxjtkyy.standardcloud.common.constant.DocConstant;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.utils.BusiUtil;
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
            log.error(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), "系统异常",e);
        }
        return response;
    }
}
