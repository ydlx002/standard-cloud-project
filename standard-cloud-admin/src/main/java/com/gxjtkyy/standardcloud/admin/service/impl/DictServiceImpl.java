package com.gxjtkyy.standardcloud.admin.service.impl;

import com.gxjtkyy.standardcloud.admin.dao.DictMapper;
import com.gxjtkyy.standardcloud.admin.domain.vo.DictDetailVO;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.AddDictReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryDictPageReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryDocPageReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.UpdateDictReq;
import com.gxjtkyy.standardcloud.admin.service.DictService;
import com.gxjtkyy.standardcloud.common.constant.DocConstant;
import com.gxjtkyy.standardcloud.common.constant.ResultCode;
import com.gxjtkyy.standardcloud.common.domain.Page;
import com.gxjtkyy.standardcloud.common.domain.dto.CondictionDTO;
import com.gxjtkyy.standardcloud.common.domain.dto.DictDTO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.exception.DocException;
import com.gxjtkyy.standardcloud.common.exception.SystemException;
import com.gxjtkyy.standardcloud.common.utils.BusiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Package com.gxjtkyy.standardcloud.admin.service.impl
 * @Author lizhenhua
 * @Date 2018/7/2 16:22
 */
@Slf4j
@Service
public class DictServiceImpl implements DictService{

    @Resource
    @Qualifier("DictMapper")
    private DictMapper dictMapper;

    @Override
    public ResponseVO addDict(AddDictReq request) throws SystemException {
        DictDTO query = new DictDTO();
        query.setDictCode(request.getDictCode());
        if(dictMapper.getList(query).size() > 0){
            log.error(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), BusiUtil.getBusiDesc(), "字典编码("+request.getDictCode()+")已存在");
            throw new SystemException(ResultCode.RESULT_CODE_1023, ResultCode.RESULT_DESC_1023);
        }
        if(dictMapper.getByName(request.getDictName()).size() > 0){
            log.error(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), BusiUtil.getBusiDesc(), "字典名称("+request.getDictName()+")已存在");
            throw new SystemException(ResultCode.RESULT_CODE_1024, ResultCode.RESULT_DESC_1024);
        }
        DictDTO dto = new DictDTO();
        dto.setUpdateTime(new Date());
        dto.setCreateTime(new Date());
        BeanUtils.copyProperties(request, dto);
        ResponseVO response = new ResponseVO();
        if( ! dictMapper.insert(dto)){
            response.setCode(ResultCode.RESULT_CODE_9999);
            response.setMsg(ResultCode.RESULT_DESC_9999);
        }
        return response;
    }

    @Override
    public ResponseVO getListByPage(QueryDictPageReq request) throws SystemException {
        int currentPage = request.getCurrentPage();
        int pageSize = request.getPageSize();

        if (currentPage < 1) {
            throw new SystemException(ResultCode.RESULT_CODE_1018, ResultCode.RESULT_DESC_1018);
        }
        if (pageSize < 1) {
            throw new SystemException(ResultCode.RESULT_CODE_1019, ResultCode.RESULT_DESC_1019);
        }
        CondictionDTO condiction = new CondictionDTO();
        condiction.setStart((currentPage - 1) * pageSize);
        condiction.setPageSize(pageSize);
        DictDTO dict = new DictDTO();
        dict.setDictCode(request.getDictCode());
        dict.setDictName(request.getDictName());
        dict.setDictType(request.getDictType());
        condiction.setDto(dict);
        int count = dictMapper.getTotalCount(condiction);
        List<DictDTO> dicts = dictMapper.getListByPage(condiction);
        List<DictDetailVO> dictDetailVOS = new ArrayList<>();
        for(DictDTO dto : dicts){
            DictDetailVO vo = new DictDetailVO();
            BeanUtils.copyProperties(dto, vo);
            dictDetailVOS.add(vo);
        }
        ResponseVO response = new ResponseVO();
        Page<DictDetailVO> page = new Page<DictDetailVO>();
        page.setCount(count);
        page.setCurrentPage(request.getCurrentPage());
        page.setPageSize(request.getPageSize());
        page.setDataList(dictDetailVOS);
        response.setData(page);
        return response;
    }

    @Override
    public ResponseVO updateDict(UpdateDictReq request) throws SystemException {
        if(null == request.getId()){
            throw new SystemException(ResultCode.RESULT_CODE_1021, ResultCode.RESULT_DESC_1021);
        }
        DictDTO dto = new DictDTO();
        dto.setId(request.getId());
        List<DictDTO> list = dictMapper.getList(dto);
        if(list.size() == 0){
            throw new SystemException(ResultCode.RESULT_CODE_1022, ResultCode.RESULT_DESC_1022);
        }
        BeanUtils.copyProperties(request, dto);
        dto.setUpdateTime(new Date());
        if(!dictMapper.update(dto)){
            throw new SystemException(ResultCode.RESULT_CODE_9999, ResultCode.RESULT_DESC_9999);
        }
        return new ResponseVO();
    }
}
