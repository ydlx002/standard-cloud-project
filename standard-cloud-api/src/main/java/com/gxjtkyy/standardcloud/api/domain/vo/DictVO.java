package com.gxjtkyy.standardcloud.api.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 字典VO
 * @Package com.gxjtkyy.domain.vo
 * @Author lizhenhua
 * @Date 2018/5/30 8:36
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
public class DictVO implements Serializable{

    /**字典码*/
    private String dictCode;

    /**字典名*/
    private String dictName;


}
