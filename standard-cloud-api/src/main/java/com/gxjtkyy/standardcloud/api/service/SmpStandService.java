package com.gxjtkyy.standardcloud.api.service;


import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.BaseException;

/**
 * 抽样细则服务接口
 * @Package com.gxjtkyy.service
 * @Author lizhenhua
 * @Date 2018/6/25 17:55
 */
public interface SmpStandService extends StandDocService{

    /**
     * 根据文档ID获取详细抽样细则文档
     * @param docId
     * @return
     * @throws BaseException
     */
    ResponseVO getSmpDetailDoc(String docId) throws BaseException;
}
