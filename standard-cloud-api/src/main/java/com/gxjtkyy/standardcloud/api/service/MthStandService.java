package com.gxjtkyy.standardcloud.api.service;


import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryDeteMthPageReq;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryReferDocReq;
import com.gxjtkyy.standardcloud.common.exception.BaseException;

/**
 * 方法标准接口
 * @Package com.gxjtkyy.service
 * @Author lizhenhua
 * @Date 2018/6/21 8:39
 */
public interface MthStandService extends StandDocService{


    /**
     * 分页获取检测方法请求
     * @param request 请求实体对象
     * @return
     * @throws BaseException
     */
    ResponseVO getDeteMthList(QueryDeteMthPageReq request) throws BaseException;


    /**
     * 查询相关产品
     * @param request 请求实体对象
     * @return
     * @throws BaseException
     */
    ResponseVO getReferDoc(QueryReferDocReq request) throws BaseException;

}
