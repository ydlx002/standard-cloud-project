package com.gxjtkyy.standardcloud.admin.domain.vo.request;

import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import com.gxjtkyy.standardcloud.common.validation.annotation.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 更新字典值请求体
 * @Package com.gxjtkyy.standardcloud.admin.domain.vo.request
 * @Author lizhenhua
 * @Date 2018/7/3 8:31
 */
@Setter
@Getter
@ToString(callSuper = true)
public class UpdateDictReq extends RequestVO{

    /**id*/
    @NotNull
    private Integer id;

    /**字典编码*/
    private String dictCode;

    /**字典名称*/
    private String dictName;

    /**字典描述*/
    private String dictDesc;

    /**字典类型*/
    private Integer dictType;


}
