package com.gxjtkyy.standardcloud.api.service.impl;


import com.gxjtkyy.standardcloud.common.domain.Page;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.api.service.DeterStandService;
import com.gxjtkyy.standardcloud.common.constant.DocTemplate;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.exception.DocException;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
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

import static com.gxjtkyy.standardcloud.common.constant.ResultCode.RESULT_CODE_1011;
import static com.gxjtkyy.standardcloud.common.constant.ResultCode.RESULT_DESC_1011;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * 接口服务实现
 * @Package com.gxjtkyy.service.impl
 * @Author lizhenhua
 * @Date 2018/6/26 8:22
 */
@Service
public class DeterStandServiceImpl extends StandDocServiceImpl implements DeterStandService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ResponseVO getDocsByMthNo(String deteBasis, int currentPage, int pageSize) throws BaseException {
        if (StringUtils.isEmpty(deteBasis)) {
            throw new DocException(RESULT_CODE_1011, RESULT_DESC_1011);
        }

        List<AggregationOperation> commonOperations = new ArrayList<>();

        commonOperations.add(project("content.deterStand").andExclude("_id"));
        commonOperations.add(unwind("deterStand"));
        commonOperations.add(Aggregation.match(Criteria.where("deterStand.deteBasis").is(deteBasis)));

        //分页条件
        List<AggregationOperation> pageOperations = new ArrayList<>(commonOperations);
        pageOperations.add(skip((currentPage - 1) * pageSize));
        pageOperations.add(limit(pageSize));

        //总数条件
        List<AggregationOperation> totalAggOperation = new ArrayList<>(commonOperations);
        totalAggOperation.add(group().count().as("total"));

        List<Map> deterMaps = mongoTemplate.aggregate(Aggregation.newAggregation(pageOperations), DocTemplate.DETER_STAND.getTableName(), Map.class).getMappedResults();

        DBObject rawResults = mongoTemplate.aggregate(Aggregation.newAggregation(totalAggOperation), DocTemplate.DETER_STAND.getTableName(), Map.class).getRawResults();
        BasicDBList result = (BasicDBList) rawResults.get("result");
        int total = result.isEmpty() ? 0 : Integer.valueOf(((DBObject) result.get(0)).get("total").toString());

        List<Object> list = new ArrayList<>();
        for(Map map : deterMaps){
            list.add(map.get("deterStand"));
        }

        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setCount(total);
        page.setDataList(list);
        ResponseVO response = new ResponseVO();
        response.setData(page);
        return response;
    }
}
