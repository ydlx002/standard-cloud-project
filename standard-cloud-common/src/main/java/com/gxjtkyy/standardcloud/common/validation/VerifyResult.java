package com.gxjtkyy.standardcloud.common.validation;

import com.gxjtkyy.standardcloud.common.constant.ResultCode;

/**
 * 校验结果
 * @Package io.github.ydlx.constant
 * @Author lizhenhua
 * @Date 2018/6/22 9:06
 */

public class VerifyResult {

    public VerifyResult(){
        this.isVerify = true;
        this.setResultCode(ResultCode.RESULT_CODE_0000);
        this.setResultDesc(ResultCode.RESULT_DESC_0000);
    }

    private boolean isVerify;

    /**结果码*/
    private String resultCode;

    /**结果描述*/
    private String resultDesc;

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    @Override
    public String toString() {
        return "VerifyResult{" +
                "isVerify=" + isVerify +
                ", resultCode='" + resultCode + '\'' +
                ", resultDesc='" + resultDesc + '\'' +
                '}';
    }
}
