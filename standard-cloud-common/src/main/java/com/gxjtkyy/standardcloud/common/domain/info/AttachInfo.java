package com.gxjtkyy.standardcloud.common.domain.info;

import lombok.Data;

import java.io.Serializable;

/**
 * 附件信息
 * @Package com.gxjtkyy.domain
 * @Author lizhenhua
 * @Date 2018/6/20 12:16
 */
@Data
public class AttachInfo implements Serializable{

    /**id*/
    private String id;

    /**附件名字*/
    private String attachName;

    /**url*/
    private String url;


}
