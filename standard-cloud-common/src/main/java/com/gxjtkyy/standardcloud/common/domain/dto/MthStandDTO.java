package com.gxjtkyy.standardcloud.common.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 方法标准数据对象
 * @Package com.gxjtkyy.domain.dto
 * @Author lizhenhua
 * @Date 2018/5/24 9:50
 */
@Setter
@Getter
@ToString
public class MthStandDTO extends BaseDocDTO {

    /**常规数据*/
    private Object generalData;

    /**检测方法列*/
    private List<DetectMthDTO> detectMthList;

    /**附录ID列*/
    private List<String> attachIds;

}
