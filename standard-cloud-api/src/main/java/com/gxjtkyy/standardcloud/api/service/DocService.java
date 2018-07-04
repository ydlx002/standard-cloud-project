package com.gxjtkyy.standardcloud.api.service;


import com.gxjtkyy.standardcloud.api.domain.vo.request.KeySearchReq;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryDictReq;
import com.gxjtkyy.standardcloud.common.domain.dto.BriefDocDTO;
import com.gxjtkyy.standardcloud.common.domain.dto.DetailDocDTO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.exception.DocException;

/**
 * 标准文档接口
 * @Package com.gxjtkyy.service
 * @Author lizhenhua
 * @Date 2018/5/29 9:02
 */
public interface DocService {


    /**
     * 根据关键字查询文档
     * @param request
     * @return
     */
    ResponseVO searchDocs(KeySearchReq request) throws BaseException;


    /**
     * 根据文档ID和文档类型查看
     * @param docId 文档ID
     * @param docType 文档类型
     * @return
     */
    DetailDocDTO getDoc(String docId, Integer docType) throws DocException;


    /**
     * 根据文档ID获取文档类型
     * @param docId 文档ID
     * @return
     * @throws DocException
     */
    Integer getDocType(String docId) throws DocException;


    /**
     * 根据文档ID获取模板ID
     * @param docId 文档ID
     * @return
     * @throws DocException
     */
    String getTemplateId(String docId) throws DocException;


    /**
     * 根据文档ID获取摘要文档
     * @param docId 文档ID
     * @return
     * @throws DocException
     */
    BriefDocDTO getBriefDoc(String docId) throws DocException;


    /**
     * 根据条件查询字典值
     * @param request
     * @return
     */
    ResponseVO getDicts(QueryDictReq request);

}
