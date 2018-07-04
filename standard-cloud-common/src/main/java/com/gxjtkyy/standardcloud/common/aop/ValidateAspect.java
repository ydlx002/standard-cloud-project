package com.gxjtkyy.standardcloud.common.aop;

import com.alibaba.fastjson.JSON;
import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.validation.ParamValidator;
import com.gxjtkyy.standardcloud.common.validation.VerifyResult;
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
 * 校验
 * @Package com.gxjtkyy.standardcloud.common.aop
 * @Author lizhenhua
 * @Date 2018/6/30 22:09
 */
@Aspect
@Component
@Slf4j
@Order(2)
public class ValidateAspect {

    @Autowired
    private ParamValidator paramValidator;

    @Pointcut("execution(public * com.gxjtkyy.standardcloud.*.controller..*(..))")
    private void validate() {
    }

    @Around("validate()")  // 使用上面定义的切入点
    public Object doValidate(ProceedingJoinPoint joinPoint) {
        ResponseVO response = null;
        RequestVO request = null;
        try {
            Object[] args = joinPoint.getArgs();
            for (Object obj : args) {
                if (obj instanceof RequestVO) {
                    request = (RequestVO) obj;
                    VerifyResult result = paramValidator.validate(request);
                    if(!result.isVerify()){
                        response = new ResponseVO();
                        response.setStreamNo(request.getStreamNo());
                        response.setPlatForm(request.getPlatForm());
                        response.setCode(result.getResultCode());
                        response.setMsg(result.getResultDesc());
                        return response;
                    }
                    break;
                }
            }

            Object obj = joinPoint.proceed();
            if(obj instanceof ResponseVO){
                response = (ResponseVO)obj;
                response.setStreamNo(request.getStreamNo());
                response.setPlatForm(request.getPlatForm());
                return response;
            }
        } catch (Throwable throwable) {
            log.error("拦截日志异常", throwable);
        }
        return response;
    }

}
