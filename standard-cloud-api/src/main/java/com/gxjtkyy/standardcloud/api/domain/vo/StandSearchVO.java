package com.gxjtkyy.standardcloud.api.domain.vo;

import com.gxjtkyy.standardcloud.common.domain.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 标准查询结果视图兑现对象
 * @Package com.gxjtkyy.domain.vo
 * @Author lizhenhua
 * @Date 2018/5/29 8:39
 */
@Setter
@Getter
@ToString
public class StandSearchVO extends BaseVO {

    /**文档ID*/
    private String docId;

    /**文档类型*/
    private Integer docType;

    /**文档标题*/
    private String docName;


}
