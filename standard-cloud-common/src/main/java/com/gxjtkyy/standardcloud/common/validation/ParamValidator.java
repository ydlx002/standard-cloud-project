package com.gxjtkyy.standardcloud.common.validation;



import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Set;

/**
 * @Package io.github.ydlx.validation
 * @Author lizhenhua
 * @Date 2018/6/22 8:52
 */
public class ParamValidator {

    private Validator validator;

    public ParamValidator(){

    }

    public <T> VerifyResult validate(T request){
        return this.validate(request, this.validator);
    }

    public <T> VerifyResult validate(T request, Validator validator){
        VerifyResult result = new VerifyResult();
        try {
            Set<ConstraintViolation<T>> constraintViolations = validator.validate(request);
            if(constraintViolations.size() > 0){
                ConstraintViolation<T> constraintViolation = constraintViolations.iterator().next();
                String templateMsg = constraintViolation.getMessageTemplate();
                if(templateMsg.contains("$")){
                    String[] args = templateMsg.split("$");
                    templateMsg = args[0];
                    String[] params =  Arrays.copyOfRange(args, 1,args.length);
                    result.setVerify(false);
                    result.setResultCode(templateMsg.substring(0,4));
                    result.setResultDesc(constraintViolation.getPropertyPath()+" "+String.format(templateMsg.substring(5, templateMsg.length()), params));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setVerify(false);
            result.setResultCode("9903");
            result.setResultDesc("参数校验失败");
        }
        return result;
    }

    public ParamValidator(Validator validator){
        this.validator = validator;
    }


}
