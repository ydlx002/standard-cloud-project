package com.gxjtkyy.standardcloud.admin.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import com.gxjtkyy.standardcloud.common.validation.annotation.EnumType;
import com.gxjtkyy.standardcloud.common.validation.annotation.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 更新请求体
 * @Package com.gxjtkyy.standardcloud.admin.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/7/2 11:13
 */
@Setter
@Getter
@ToString(callSuper = true)
public class UpdateTemplateReq extends RequestVO{

    /**id*/
    @NotEmpty
    private String templateId;

    /**节点*/
    @NotEmpty
    private String node;

    /**dataModel*/
    @EnumType(enums = {"TABLE","TEXT","ATTACH"})
    private String dataModel;

    /**dataDirection*/
    @EnumType(enums = {"H","V","M"})
    private String dataDirection;
    
    /**模板描述*/
    private String templateDesc;


}
