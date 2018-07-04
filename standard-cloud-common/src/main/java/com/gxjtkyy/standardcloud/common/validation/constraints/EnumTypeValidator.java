package com.gxjtkyy.standardcloud.common.validation.constraints;


import com.gxjtkyy.standardcloud.common.constant.ResultCode;
import com.gxjtkyy.standardcloud.common.validation.annotation.EnumType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Package io.github.ydlx.constraints
 * @Author lizhenhua
 * @Date 2018/7/2 15:33
 */
public class EnumTypeValidator extends BaseValidator implements ConstraintValidator<EnumType,Object> {

    private Object [] enums;


    @Override
    public void initialize(EnumType constraintAnnotation) {
        enums =  constraintAnnotation.enums();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(null != value && String.valueOf(value).length() > 0){
            for(Object obj : enums){
                if(obj.equals(value)){
                    return true;
                }
            }
            this.setResultCodeWithMsg(context, ResultCode.RESULT_CODE_0002);
            return false;
        }
        return true;
    }

}
