package com.gxjtkyy.standardcloud.common.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 抽样细则实体类
 * @Package com.gxjtkyy.domain.dto
 * @Author lizhenhua
 * @Date 2018/5/28 11:25
 */
@Setter
@Getter
@ToString
public class SampStandDTO extends BaseDocDTO {

    /**分类总则*/
    private Object classifyRule;
    
    /**抽样方法*/
    private Object sampMth;

    /**抽样样品检验项目*/
    private Object sampDetectItem;


}
