package com.gxjtkyy.standardcloud.common.aop;

import com.alibaba.fastjson.JSON;
import com.gxjtkyy.standardcloud.common.annotation.ApiAction;
import com.gxjtkyy.standardcloud.common.annotation.LogAction;
import com.gxjtkyy.standardcloud.common.constant.DocConstant;
import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.utils.BusiUtil;
import com.gxjtkyy.standardcloud.common.utils.LogUtil;
import com.gxjtkyy.standardcloud.common.validation.ParamValidator;
import com.gxjtkyy.standardcloud.common.validation.VerifyResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * API切面
 *
 * @Package com.gxjtkyy.standardcloud.common.aop
 * @Author lizhenhua
 * @Date 2018/7/3 14:31
 */
@Aspect
@Component
@Slf4j
public class ApiAspect {

    @Autowired
    private HttpServletRequest httpRequest;

    @Autowired
    private ParamValidator paramValidator;


    @Around("@annotation(apiAction)")
    public Object addActionLog(ProceedingJoinPoint joinPoint, ApiAction apiAction) {

        boolean logFlag = apiAction.saveLog();
        boolean authFlag = apiAction.auth();

        String logIndex = BusiUtil.setLogIndex(UUID.randomUUID().toString().replace("-", ""));
        String busiDesc = BusiUtil.setBusiDesc(apiAction.value());

        RequestVO request = null;
        Object[] args = joinPoint.getArgs();

        for (Object obj : args) {
            if (obj instanceof RequestVO) {
                request = (RequestVO) obj;
                VerifyResult result = paramValidator.validate(obj);
                if (!result.isVerify()) {
                    ResponseVO response = new ResponseVO();
                    response.setStreamNo(request.getStreamNo());
                    response.setPlatForm(request.getPlatForm());
                    response.setCode(result.getResultCode());
                    response.setMsg(result.getResultDesc());
                    //异步保存日志
                    LogUtil.saveLog(logIndex, busiDesc, httpRequest.getRequestURI(), httpRequest.getRemoteAddr(),
                            "", "", request, response, logFlag);
                    return response;
                }
                break;
            }
        }

        Object result = null;
        try {
            result = joinPoint.proceed();
            ResponseVO response = (ResponseVO)result;
            response.setStreamNo(request.getStreamNo());
            response.setPlatForm(request.getPlatForm());
            //异步保存日志
            LogUtil.saveLog(logIndex, busiDesc, httpRequest.getRequestURI(), httpRequest.getRemoteAddr(),
                    "", "", request, response, logFlag);
        } catch (Throwable throwable) {
            log.error(DocConstant.LOG_PRINT_FORMAT, logIndex, "切面异常", throwable);
        } finally {
            BusiUtil.removeLogIndex();
            BusiUtil.removeBusiDesc();
        }
        return result;
    }
}
