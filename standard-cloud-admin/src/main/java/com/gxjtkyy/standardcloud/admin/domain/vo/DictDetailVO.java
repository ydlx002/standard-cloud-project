package com.gxjtkyy.standardcloud.admin.domain.vo;

import com.gxjtkyy.standardcloud.common.domain.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 *  字典详细
 * @Package com.gxjtkyy.standardcloud.admin.domain.vo
 * @Author lizhenhua
 * @Date 2018/7/2 10:49
 */
@Setter
@Getter
@ToString(callSuper = true)
public class DictDetailVO extends BaseVO{

    /**序号*/
    private Integer id;

    /**字典码*/
    private String dictCode;

    /**字典类型*/
    private Integer dictType;

    /**字典名称*/
    private String dictName;

    /**字典描述*/
    private String dictDesc;

    /**操作员*/
    private String operator;

    /**操作员ID*/
    private String operatorId;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;


}
