package com.gxjtkyy.standardcloud.admin.service;

import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryTemplatePageReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryTemplateReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.UpdateTemplateReq;
import com.gxjtkyy.standardcloud.common.domain.dto.TemplateDTO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.TemplateException;

/**
 * 模板服务接口
 * @Package com.gxjtkyy.standardcloud.admin.service
 * @Author lizhenhua
 * @Date 2018/6/27 11:58
 */
public interface TemplateService {

    /**
     * 新增模板
     * @param templateName 模板名称
     * @param path 文档类型
     * @param path 模板路径
     */
    void add(String templateName, int docType, String path) throws TemplateException;


    /**
     * 分页查询模板列表
     * @param request
     * @return
     * @throws TemplateException
     */
    ResponseVO getListByPage(QueryTemplatePageReq request) throws TemplateException;


    /**
     * 获取所有的模板
     * @return
     * @throws TemplateException
     */
    ResponseVO getAllTemplate() throws TemplateException;


    /**
     * 更新模板
     * @param request 更新请求体
     * @return
     * @throws TemplateException
     */
    ResponseVO updateTemplate(UpdateTemplateReq request) throws TemplateException;


    /**
     * 查询模板详情
     * @param request
     * @return
     * @throws TemplateException
     */
    ResponseVO getTemplateDetail(QueryTemplateReq request) throws TemplateException;


    /**
     * 根据模板Id查询模板详情
     * @param templateId 模板ID
     * @return
     * @throws TemplateException
     */
    TemplateDTO getTemplateById(String templateId) throws TemplateException;

}
