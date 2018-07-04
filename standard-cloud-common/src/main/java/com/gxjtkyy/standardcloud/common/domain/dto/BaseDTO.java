package com.gxjtkyy.standardcloud.common.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据传输对象基础类
 * @Package com.gxjtkyy.domain.dto
 * @Author lizhenhua
 * @Date 2018/5/24 9:50
 */

@Setter
@Getter
@ToString
public class BaseDTO implements Serializable{

    private Date createTime;

    private Date updateTime;

}
