package com.gxjtkyy.standardcloud.common.validation.constraints;

import com.gxjtkyy.standardcloud.common.constant.ResultCode;
import com.gxjtkyy.standardcloud.common.validation.annotation.StringLength;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验器
 * @Package io.github.ydlx.constraints
 * @Author lizhenhua
 * @Date 2018/6/21 15:13
 */
public class StringLengthValidator extends BaseValidator implements ConstraintValidator<StringLength,Object> {

    private long min;
    private long max;

    public void initialize(StringLength stringLength) {
        min = stringLength.min();
        max = stringLength.max();
    }

    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(null != value && value instanceof String){
            if(String.valueOf(value).length() <min || String.valueOf(value).length() > max){
                this.setResultCodeWithMsg(constraintValidatorContext, ResultCode.RESULT_CODE_0003, min, max);
                return false;
            }
        }
        return true;
    }

}
