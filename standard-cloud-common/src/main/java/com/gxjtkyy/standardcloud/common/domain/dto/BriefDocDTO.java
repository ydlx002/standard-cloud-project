package com.gxjtkyy.standardcloud.common.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Package com.gxjtkyy.domain.dto
 * @Author lizhenhua
 * @Date 2018/5/29 12:25
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class BriefDocDTO extends BaseDocDTO {

    public BriefDocDTO(BaseDocDTO dto){
        this.setDocId(dto.getDocId());
        this.setDocName(dto.getDocName());
        this.setDocType(dto.getDocType());
        this.setTemplateId(dto.getTemplateId());
        this.setViewCount(new Integer(0));
        this.setUpdateTime(dto.getUpdateTime());
        this.setCreateTime(dto.getCreateTime());
    }

    /**阅读量*/
    private int viewCount;

}
