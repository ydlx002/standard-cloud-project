package com.gxjtkyy.standardcloud.api;

import com.alibaba.fastjson.JSON;
import com.gxjtkyy.standardcloud.common.constant.DocTemplate;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * @Package com.gxjtkyy
 * @Author lizhenhua
 * @Date 2018/6/15 8:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTemplateTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testMongoTemplate(){

        DBObject con = new BasicDBObject();
        //等于条件
        con.put("_id","b727649ecbef4c7fb6cc5cb8e5da7639");

        DBObject field = new BasicDBObject();
        field.put("content",1);
        field.put("content.paramList",0);
        field.put("_id",0);


//        Criteria criteria = new Criteria("_id");
//        criteria.is("b727649ecbef4c7fb6cc5cb8e5da7639");
        Query query = new BasicQuery(con, field);
        Map map = mongoTemplate.findOne(query, Map.class, DocTemplate.PRO_STAND.getTableName());

        System.out.println(JSON.toJSONString(map));
        //mongoTemplate.find();
    }


    /**
     * 内嵌文档分页查询
     */
    @Test
    public void pageQuery() {
        String topCategory = "传统型半干黄酒";

        List<AggregationOperation> commonOperations = new ArrayList<>(9);
        //commonOperations.add(project("content.paramList").and("content.paramList").as("detail"));
        commonOperations.add(Aggregation.match(Criteria.where("_id").is("d6c6c2d49f6445708a2ab6c51cb12ee8")));
        commonOperations.add(project("content.paramList").andExclude("_id"));
       //Optional.ofNullable(topCategory).ifPresent(s -> commonOperations.add(match(where("paramList.topCategory").is(topCategory))));

       commonOperations.add(unwind("paramList"));

       commonOperations.add(Aggregation.match(Criteria.where("paramList.topCategory").is(topCategory)));

        List<AggregationOperation> pageOperations = new ArrayList<>(commonOperations);
        pageOperations.add(skip(1l));
        pageOperations.add(limit(10));


        List<AggregationOperation> totalAggOperation = new ArrayList<>(commonOperations);
        totalAggOperation.add(group().count().as("total"));

        List<Map> results = mongoTemplate.aggregate(Aggregation.newAggregation(pageOperations), DocTemplate.PRO_STAND.getTableName(), Map.class).getMappedResults();

        System.out.println(results.size());

        DBObject rawResults = mongoTemplate.aggregate(Aggregation.newAggregation(totalAggOperation), DocTemplate.PRO_STAND.getTableName(),Map.class).getRawResults();
        BasicDBList result = (BasicDBList)rawResults.get("result");
        long total = result.isEmpty() ? 0 : Long.parseLong(((DBObject)result.get(0)).get("total").toString());

        System.out.println("total:" + total);
    }
}
