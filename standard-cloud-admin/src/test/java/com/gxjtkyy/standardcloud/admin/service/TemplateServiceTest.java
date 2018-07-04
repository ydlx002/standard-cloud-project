package com.gxjtkyy.standardcloud.admin.service;

import com.alibaba.fastjson.JSON;
import com.gxjtkyy.standardcloud.common.constant.TemplateConstant;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Package com.gxjtkyy.standardcloud.admin.service
 * @Author lizhenhua
 * @Date 2018/6/27 13:10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TemplateServiceTest {


    @Autowired
    private TemplateService templateService;



    @Test
    public void add() throws Exception {

    }

    @Test
    public void getListByPage() throws Exception {

    }

    @Test
    public void getAllTemplate() throws Exception {
        ResponseVO response = templateService.getAllTemplate();
        System.out.println(JSON.toJSONString(response));
    }

}