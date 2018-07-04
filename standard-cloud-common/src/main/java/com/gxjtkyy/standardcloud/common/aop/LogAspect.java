package com.gxjtkyy.standardcloud.common.aop;

import com.alibaba.fastjson.JSON;
import com.gxjtkyy.standardcloud.common.annotation.LogAction;
import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面
 *
 * @Package com.gxjtkyy.quality.aop
 * @Author lizhenhua
 * @Date 2018/6/6 15:56
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class LogAspect {

    @Autowired
    private HttpServletRequest httpRequest;


    @Around("@annotation(logAction)")
    public Object addActionLog(ProceedingJoinPoint joinPoint, LogAction logAction){

        Object[] args = joinPoint.getArgs();
        Object request = null;
        for (Object obj : args) {
            if (obj instanceof RequestVO) {
                request = obj;
                break;
            }
        }

        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("path-->{},\n ip-->{}, \n request-->{},\n response-->{}",httpRequest.getRequestURI(), httpRequest.getRemoteAddr(), JSON.toJSON(request), JSON.toJSON(obj));
        return obj;
    }

}
