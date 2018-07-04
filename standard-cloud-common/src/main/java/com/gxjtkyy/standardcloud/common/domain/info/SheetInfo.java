package com.gxjtkyy.standardcloud.common.domain.info;

import com.gxjtkyy.standardcloud.common.constant.TemplateConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 表格信息
 * @Package com.gxjtkyy.domain
 * @Author lizhenhua
 * @Date 2018/6/11 11:24
 */
@Setter
@Getter
@ToString
public class SheetInfo implements Serializable{

    /**字典码*/
    private String sheetCode;

    /**是否为单列表格*/
    private boolean isSingleColumn;

    /**列属性*/
    private List<String> columns;

    /**起始行*/
    private int startRow;

    /**数据读取方向*/
    private String dataDirection = TemplateConstant.DATA_DIRECTION_HORIZONTAL;

    /**数据模型 table text attach*/
    private String dataModel = TemplateConstant.DATA_MODEL_TEXT;

}
