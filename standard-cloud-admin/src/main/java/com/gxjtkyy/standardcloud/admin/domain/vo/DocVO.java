package com.gxjtkyy.standardcloud.admin.domain.vo;

import com.gxjtkyy.standardcloud.common.domain.vo.BaseVO;
import lombok.Data;

import java.util.Date;

/**
 * 文档VO
 * @Package com.gxjtkyy.standardcloud.admin.domain.vo
 * @Author lizhenhua
 * @Date 2018/6/28 8:16
 */
@Data
public class DocVO extends BaseVO{

    /**id*/
    private String docId;
    
    /**文档名称*/
    private String docName;
    
    /**文档类型*/
    private Integer docType;

    /**创建时间*/
    private Date createTime;
}
