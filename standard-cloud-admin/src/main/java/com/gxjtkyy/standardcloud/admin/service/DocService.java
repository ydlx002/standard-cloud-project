package com.gxjtkyy.standardcloud.admin.service;

import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryDocPageReq;
import com.gxjtkyy.standardcloud.common.domain.vo.DocRequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.exception.DocException;

/**
 * 文档服务接口
 * @Package com.gxjtkyy.standardcloud.admin.service
 * @Author lizhenhua
 * @Date 2018/6/27 17:10
 */
public interface DocService {


    /**
     * 新增文档
     * @param name 文件名称
     * @param templateId 模板ID
     * @param docPath 文档路径
     */
    void addDoc(String name, String templateId, String docPath) throws BaseException;


    /**
     * 根据文档ID获取摘要文档
     * @param request 查询条件
     * @return
     * @throws DocException
     */
    ResponseVO getListByPage(QueryDocPageReq request) throws DocException;


    /**
     * 删除文档
     * @param request
     * @return
     * @throws DocException
     */
    ResponseVO deleteDoc(DocRequestVO request) throws DocException;

}
