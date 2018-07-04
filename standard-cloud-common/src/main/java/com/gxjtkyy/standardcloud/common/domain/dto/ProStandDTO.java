package com.gxjtkyy.standardcloud.common.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * 产品标准文档数据对象
 * @Package com.gxjtkyy.domain.dto
 * @Author lizhenhua
 * @Date 2018/5/22 19:12
 */
@Setter
@Getter
@ToString
public class ProStandDTO extends BaseDocDTO {

    /**常规数据*/
    private Object generalData;

    /**检测方法列*/
    private List<DetectMthDTO> detectMthList;

    /**术语和定义*/
    private List<String> termList;

    /**分类*/
    private List<String> categoryList;

    /**要求*/
    private List<String> requirementList;

    /**检验规则*/
    private List<String> detectRuleList;

    /**标志和包装*/
    private List<String> symbolPackageList;

    /**运输和贮存*/
    private List<String> transStorageList;

    /**附录ID列*/
    private List<String> attachIds;

    /**参数列表*/
    private List<Map<String, Object>> paramList;
}
