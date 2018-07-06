package com.gxjtkyy.standardcloud.admin.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 移除模板
 * @Package com.gxjtkyy.standardcloud.admin.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/7/6 16:50
 */
@Setter
@Getter
@ToString(callSuper = true)
public class RemoveTemplateReq extends RequestVO{

    /**模板ID*/
    private String templateId;


}
