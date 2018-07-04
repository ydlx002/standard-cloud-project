package com.gxjtkyy.standardcloud.api.service;


import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.BaseException;

/**
 * 判定标准服务接口
 * @Package com.gxjtkyy.service
 * @Author lizhenhua
 * @Date 2018/6/26 8:21
 */
public interface DeterStandService extends StandDocService{


    /**
     * 检测依据(即方法标准编号)查询判定标准
     * @param deteBasis 检测依据，方法标准编号
     * @param currentPage 当前页码
     * @param pageSize 页面大小
     * @return
     * @throws BaseException
     */
    ResponseVO getDocsByMthNo(String deteBasis, int currentPage, int pageSize) throws BaseException;
}
