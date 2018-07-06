package com.gxjtkyy.standardcloud.api.service;



import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryDeteMthReq;
import com.gxjtkyy.standardcloud.common.domain.dto.TemplateDTO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.exception.TemplateException;

import java.util.List;

/**
 * 标准文档视图服务接口
 * @Package com.gxjtkyy.service
 * @Author lizhenhua
 * @Date 2018/6/14 15:01
 */
public interface StandDocService {

    /**
     * 根据模板ID查询模板
     * @param id
     * @return
     */
    TemplateDTO getTemplateById(String id) throws TemplateException;

    /**
     * 获取导航
     * @param docId 文档ID
     * @return
     * @throws BaseException
     */
    ResponseVO getNavigation(String docId) throws BaseException;

    /***
     * 获取文本型数据文档
     * 相对动态数据和复杂数据而言（如表格）
     * @param docId 文档ID
     * @return
     * @throws BaseException
     */
    ResponseVO getTextDoc(String docId) throws BaseException;


    /**
     * 获取表格类文档
     * @param docId 文档ID
     * @return  返回分页对象
     * @throws BaseException
     */
    ResponseVO getTableDoc(String docId) throws BaseException;

    /**
     * 获取附录类文档
     * @param docId 文档ID
     * @return  返回分页对象
     * @throws BaseException
     */
    ResponseVO getAttachDoc(String docId) throws BaseException;


    /**
     * 更新文档附件
     * @param docId 文档ID
     * @param docType   文档类型
     * @param node  节点
     * @param object 更新内容
     * @return
     */
     ResponseVO  addDocAttach(String docId, Integer docType, String node, Object object) throws BaseException;


    /**
     * 获取条件获取内嵌文档列表
     * 此处条件为key-value对应值
     * @param docId 文档ID
     * @param docType 文档类型
     * @param node  节点
     * @param key 键--条件
     * @param value 值
     * @return
     * @throws BaseException
     */
     List<Object> getContentDoc(String docId, Integer docType, String node, String key, String value) throws BaseException;


}
