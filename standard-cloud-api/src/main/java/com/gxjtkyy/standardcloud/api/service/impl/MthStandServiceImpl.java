package com.gxjtkyy.standardcloud.api.service.impl;


import com.gxjtkyy.standardcloud.common.domain.Page;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryDeteMthPageReq;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryReferDocReq;
import com.gxjtkyy.standardcloud.api.service.DeterStandService;
import com.gxjtkyy.standardcloud.api.service.MthStandService;
import com.gxjtkyy.standardcloud.common.constant.DocTemplate;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.exception.DocException;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.gxjtkyy.standardcloud.common.constant.ResultCode.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * 方法标准接口实现类
 * @Package com.gxjtkyy.service.impl
 * @Author lizhenhua
 * @Date 2018/6/21 8:40
 */
@Service
@Slf4j
public class MthStandServiceImpl extends StandDocServiceImpl implements MthStandService {


    @Autowired
    private DeterStandService deterStandService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ResponseVO getDeteMthList(QueryDeteMthPageReq request) throws BaseException {
        if (null == request.getDocId()) {
            throw new DocException(RESULT_CODE_1009, RESULT_DESC_1009);
        }

        List<AggregationOperation> commonOperations = new ArrayList<>();
        commonOperations.add(Aggregation.match(Criteria.where("_id").is(request.getDocId())));
        commonOperations.add(project("content.deteMth").andExclude("_id"));
        commonOperations.add(unwind("deteMth"));

        //分页条件
        List<AggregationOperation> pageOperations = new ArrayList<>(commonOperations);
        pageOperations.add(skip((request.getCurrentPage() - 1) * request.getPageSize()));
        pageOperations.add(limit(request.getPageSize()));

        //总数条件
        List<AggregationOperation> totalAggOperation = new ArrayList<>(commonOperations);
        totalAggOperation.add(group().count().as("total"));

        List<Map> paramList = mongoTemplate.aggregate(Aggregation.newAggregation(pageOperations), DocTemplate.MTH_STAND.getTableName(), Map.class).getMappedResults();

        DBObject rawResults = mongoTemplate.aggregate(Aggregation.newAggregation(totalAggOperation), DocTemplate.MTH_STAND.getTableName(), Map.class).getRawResults();
        BasicDBList result = (BasicDBList) rawResults.get("result");
        int total = result.isEmpty() ? 0 : Integer.valueOf(((DBObject) result.get(0)).get("total").toString());

        List<Object> list = new ArrayList<>();
        for(Map map : paramList){
            list.add(((Map)map.get("deteMth")).get("deteItem"));
        }

        Page page = new Page();
        page.setCurrentPage(request.getCurrentPage());
        page.setPageSize(request.getPageSize());
        page.setCount(total);
        page.setDataList(list);
        ResponseVO response = new ResponseVO();
        response.setData(page);
        return response;
    }

    @Override
    public ResponseVO getReferDoc(QueryReferDocReq request) throws BaseException {
        return deterStandService.getDocsByMthNo(request.getDeteBasis(), request.getCurrentPage(), request.getPageSize());
    }
}
