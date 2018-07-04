package com.gxjtkyy.standardcloud.admin.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import com.gxjtkyy.standardcloud.common.validation.annotation.NotEmpty;
import com.gxjtkyy.standardcloud.common.validation.annotation.Regex;
import com.gxjtkyy.standardcloud.common.validation.annotation.StringLength;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字典添加请求体
 * @Package com.gxjtkyy.standardcloud.admin.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/7/4 10:25
 */
@Setter
@Getter
@ToString(callSuper = true)
public class AddDictReq extends RequestVO{


    /**字典码*/
    @NotEmpty
    @StringLength(min = 1, max = 28)
    @Regex("^[A-Za-z]+$")
    private String dictCode;

    /**字典类型*/
    private Integer dictType =0;

    /**字典名称*/
    @NotEmpty
    private String dictName;

    /**字典描述*/
    private String dictDesc;

}
