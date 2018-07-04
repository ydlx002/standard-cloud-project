package com.gxjtkyy.standardcloud.api.service;

import com.gxjtkyy.standardcloud.common.constant.DocTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Package com.gxjtkyy.service
 * @Author lizhenhua
 * @Date 2018/6/11 18:35
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TemplateServiceTest {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void getTemplateById() throws Exception {

    }





    @Test
    public void update() throws Exception{
        Query query = Query.query(Criteria.where("_id").is("d6c6c2d49f6445708a2ab6c51cb12ee8"));
        Update update = Update.update("content.attach", "MongoTemplate");
        mongoTemplate.upsert(query, update, DocTemplate.PRO_STAND.getTableName());
    }

}