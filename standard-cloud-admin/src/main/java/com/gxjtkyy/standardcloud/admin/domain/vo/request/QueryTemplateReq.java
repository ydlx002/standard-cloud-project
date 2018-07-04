package com.gxjtkyy.standardcloud.admin.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import com.gxjtkyy.standardcloud.common.validation.annotation.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 获取模板详情请求体
 * @Package com.gxjtkyy.standardcloud.admin.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/7/2 15:08
 */
@Setter
@Getter
@ToString(callSuper = true)
public class QueryTemplateReq extends RequestVO{

    /**templateId*/
    @NotEmpty
    private String templateId;
    

}
