package com.gxjtkyy.standardcloud.common.constant;

import com.gxjtkyy.standardcloud.common.exception.DocException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


/**
 * 模板类型枚举类
 * @Package com.gxjtkyy.constant
 * @Author lizhenhua
 * @Date 2018/5/29 18:48
 */
@Getter
@ToString
@AllArgsConstructor
@Slf4j
public enum DocTemplate {

    PRO_STAND(0, "产品标准文档", "DOC_PRO_STAND"),
    MTH_STAND(1, "方法标准文档", "DOC_MTH_STAND"),
    SMP_STAND(2, "抽样细则文档", "DOC_SMP_STAND"),
    DETER_STAND(3, "判定标准文档", "DOC_DETER_STAND");

    /**文档类型*/
   private Integer docType;

   /**文档模板描述*/
   private String tmplDesc;

   /**存储集合名*/
   private String tableName;


    /**
     * 根据文档类型获取模板枚举
     * @param docType
     * @return DocTemplate
     */
    public static DocTemplate getTemplateByType(int docType) throws DocException {
        for(DocTemplate docTemplate : DocTemplate.values()){
            if(docType == docTemplate.getDocType()){
                return docTemplate;
            }
        }
//        log.error("文档类型无定义 --> docType: {}", docType);
        throw new DocException(ResultCode.RESULT_CODE_1008, String.format(ResultCode.RESULT_DESC_1008, docType));
    }
}
