package com.gxjtkyy.standardcloud.common.parser;


import com.gxjtkyy.standardcloud.common.domain.dto.DetailDocDTO;
import com.gxjtkyy.standardcloud.common.domain.dto.TemplateDTO;
import com.gxjtkyy.standardcloud.common.exception.TemplateException;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * 标准文档解析器
 * @Package com.gxjtkyy.parser
 * @Author lizhenhua
 * @Date 2018/6/13 11:36
 */
public class StandDocParser extends ExcelParser<DetailDocDTO>{

    public StandDocParser(TemplateDTO template, String filePath){
        super(template, filePath);
    }

    @Override
    public DetailDocDTO doParse(String name) throws TemplateException {
        DetailDocDTO detailDoc = new DetailDocDTO();
        if(StringUtils.isEmpty(name)){
            String fileName = new File(this.excelPath).getName();
            name = fileName.substring(0, fileName.lastIndexOf("."));
        }
        detailDoc.setDocName(name);
        detailDoc.setDocType(this.getDocType());
        detailDoc.setDocId(UUID.randomUUID().toString().replace("-","").trim());
        detailDoc.setTemplateId(template.getId());
        detailDoc.setContent(this._doParse(preParse()));
        detailDoc.setCreateTime(new Date());
        detailDoc.setUpdateTime(new Date());
        return detailDoc;
    }
}
