package com.gxjtkyy.standardcloud.api.service;


import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryCategoryReq;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryParamPageReq;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.BaseException;

/**
 * 产品标准文档服务接口
 * @Package com.gxjtkyy.service
 * @Author lizhenhua
 * @Date 2018/6/16 12:01
 */
public interface ProStandService extends StandDocService{


    /**
     * 获取参数列表
     * @param request 请求体
     * @return
     * @throws BaseException
     */
    ResponseVO getParamList(QueryParamPageReq request) throws BaseException;


    /**
     * 获取分类
     * @return
     * @throws BaseException
     */
    ResponseVO getCategory(QueryCategoryReq request) throws BaseException;



}
