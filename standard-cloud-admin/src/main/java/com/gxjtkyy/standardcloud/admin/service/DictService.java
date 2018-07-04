package com.gxjtkyy.standardcloud.admin.service;

import com.gxjtkyy.standardcloud.admin.domain.vo.request.AddDictReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryDictPageReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryDocPageReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.UpdateDictReq;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.exception.DocException;
import com.gxjtkyy.standardcloud.common.exception.SystemException;

/**
 * @Package com.gxjtkyy.standardcloud.admin.service
 * @Author lizhenhua
 * @Date 2018/7/2 10:52
 */
public interface DictService {


    /**
     * 添加字典值
     * @param request
     * @return
     * @throws SystemException
     */
    ResponseVO addDict(AddDictReq request) throws SystemException;

    /**
     * 分页查询字典
     * @param request 查询条件
     * @return
     * @throws DocException
     */
    ResponseVO getListByPage(QueryDictPageReq request) throws SystemException;


    /**
     * 更新字典值
     * @param request
     * @return
     * @throws SystemException
     */
    ResponseVO updateDict(UpdateDictReq request) throws SystemException;


}
