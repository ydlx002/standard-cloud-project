package com.gxjtkyy.standardcloud.common.validation.constraints;


import com.gxjtkyy.standardcloud.common.constant.ResultCode;

import javax.validation.ConstraintValidatorContext;

/**
 * @Package io.github.ydlx.constraints
 * @Author lizhenhua
 * @Date 2018/6/22 14:20
 */
public class BaseValidator {

    protected void setResultCodeWithMsg(ConstraintValidatorContext context, String resultCode, Object ... args){
        context.disableDefaultConstraintViolation();//禁用默认的message的值
        //重新添加错误提示语句
        String resultMsg = ResultCode.getDesc(resultCode);
        String msg = resultCode+"$"+resultMsg;

        if(args != null && args.length > 0){
            msg = String.format(msg, args);
        }
        context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
    }

}
