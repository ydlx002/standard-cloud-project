package com.gxjtkyy.standardcloud.api.service.impl;


import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryCategoryReq;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryParamPageReq;
import com.gxjtkyy.standardcloud.common.domain.Page;
import com.gxjtkyy.standardcloud.api.domain.vo.CategoryVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.api.service.DocService;
import com.gxjtkyy.standardcloud.api.service.ProStandService;
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
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.gxjtkyy.standardcloud.common.constant.ResultCode.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


/**
 * 产品标准接口服务实现层
 *
 * @Package com.gxjtkyy.service.impl
 * @Author lizhenhua
 * @Date 2018/6/16 12:08
 */
@Service
@Slf4j
public class ProStandServiceImpl extends StandDocServiceImpl implements ProStandService {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public ResponseVO getParamList(QueryParamPageReq request) throws BaseException {
        if (null == request.getDocId()) {
            throw new DocException(RESULT_CODE_1009, RESULT_DESC_1009);
        }

        List<AggregationOperation> commonOperations = new ArrayList<>();
        //commonOperations.add(project("content.paramList").and("content.paramList").as("detail"));
        commonOperations.add(Aggregation.match(Criteria.where("_id").is(request.getDocId())));
        commonOperations.add(project("content.paramList").andExclude("_id"));
        commonOperations.add(unwind("paramList"));
        //查询条件
        if (!StringUtils.isEmpty(request.getTopCategory())) {
            commonOperations.add(Aggregation.match(Criteria.where("paramList.topCategory").is(request.getTopCategory())));
        }
        if (!StringUtils.isEmpty(request.getSubCategory())) {
            commonOperations.add(Aggregation.match(Criteria.where("paramList.subCategory").is(request.getSubCategory())));
        }
        if (!StringUtils.isEmpty(request.getThirdCategory())) {
            commonOperations.add(Aggregation.match(Criteria.where("paramList.thirdCategory").is(request.getThirdCategory())));
        }

        //分页条件
        List<AggregationOperation> pageOperations = new ArrayList<>(commonOperations);
        pageOperations.add(skip((request.getCurrentPage() - 1) * request.getPageSize()));
        pageOperations.add(limit(request.getPageSize()));

        //总数条件
        List<AggregationOperation> totalAggOperation = new ArrayList<>(commonOperations);
        totalAggOperation.add(group().count().as("total"));

        List<Map> paramList = mongoTemplate.aggregate(Aggregation.newAggregation(pageOperations), DocTemplate.PRO_STAND.getTableName(), Map.class).getMappedResults();

        DBObject rawResults = mongoTemplate.aggregate(Aggregation.newAggregation(totalAggOperation), DocTemplate.PRO_STAND.getTableName(), Map.class).getRawResults();
        BasicDBList result = (BasicDBList) rawResults.get("result");
        int total = result.isEmpty() ? 0 : Integer.valueOf(((DBObject) result.get(0)).get("total").toString());

        List<Object> list = new ArrayList<>();
        for(Map map : paramList){
            list.add(map.get("paramList"));
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
    public ResponseVO getCategory(QueryCategoryReq request) throws BaseException {
        if (null == request.getDocId()) {
            throw new DocException(RESULT_CODE_1009, RESULT_DESC_1009);
        }

        List<AggregationOperation> commonOperations = new ArrayList<>();
        commonOperations.add(Aggregation.match(Criteria.where("_id").is(request.getDocId())));
        commonOperations.add(project("content.paramList").andExclude("_id"));
        commonOperations.add(unwind("paramList"));
        //查询条件
        if (!StringUtils.isEmpty(request.getTopCategory())) {
            commonOperations.add(Aggregation.match(Criteria.where("paramList.topCategory").is(request.getTopCategory())));
        }
        if (!StringUtils.isEmpty(request.getSubCategory())) {
            commonOperations.add(Aggregation.match(Criteria.where("paramList.subCategory").is(request.getSubCategory())));
        }
        if (!StringUtils.isEmpty(request.getThirdCategory())) {
            commonOperations.add(Aggregation.match(Criteria.where("paramList.thirdCategory").is(request.getThirdCategory())));
        }
        List<Map> paramList = mongoTemplate.aggregate(Aggregation.newAggregation(commonOperations), DocTemplate.PRO_STAND.getTableName(), Map.class).getMappedResults();
        CategoryVO category = new CategoryVO();
        for(Map map : paramList){
            category.getTopCategories().add((String)((Map)map.get("paramList")).get("topCategory"));
            category.getSubCategories().add((String)((Map)map.get("paramList")).get("subCategory"));
            category.getThirdCategories().add((String)((Map)map.get("paramList")).get("thirdCategory"));
        }
        ResponseVO response = new ResponseVO();
        response.setData(category);
        return response;
    }

}
