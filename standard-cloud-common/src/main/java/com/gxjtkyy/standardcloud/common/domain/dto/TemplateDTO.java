package com.gxjtkyy.standardcloud.common.domain.dto;

import com.gxjtkyy.standardcloud.common.domain.info.SheetInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * 模板数据传输对象
 * @Package com.gxjtkyy.standardcloud.common.domain.dto
 * @Author lizhenhua
 * @Date 2018/7/1 22:25
 */
@Setter
@Getter
@ToString(callSuper = true)
public class TemplateDTO extends BaseDTO{

    /**id*/
    @Id
    private String id;

    /**模板名称*/
    private String templateName;
    
    /**模板描述*/
    private String templateDesc;

    /**文档类型*/
    private int docType;
    
    /**0 无效  1有效*/
    private Integer status;

    /**模板目录*/
    private List<SheetInfo> catalog;

    /**导航*/
    private LinkedHashSet<String> navigation = new LinkedHashSet<>();

}
