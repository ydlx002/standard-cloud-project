package com.gxjtkyy.standardcloud.api.service.impl;


import com.gxjtkyy.standardcloud.common.domain.dto.DetailDocDTO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.api.service.DocService;
import com.gxjtkyy.standardcloud.api.service.SmpStandService;
import com.gxjtkyy.standardcloud.common.constant.DocTemplate;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 抽样细则服务接口实现层
 * @Package com.gxjtkyy.service.impl
 * @Author lizhenhua
 * @Date 2018/6/25 18:26
 */
@Service
public class SmpStandServiceImpl extends StandDocServiceImpl implements SmpStandService {

    @Autowired
    private DocService docService;


    @Override
    public ResponseVO getSmpDetailDoc(String docId) throws BaseException {
        DetailDocDTO detailDoc = docService.getDoc(docId, DocTemplate.SMP_STAND.getDocType());
        ResponseVO response = new ResponseVO();
        if(null != detailDoc){
            response.setData(detailDoc.getContent());
        }
        return response;
    }
}
