package com.gxjtkyy.standardcloud.api.service;

import com.alibaba.fastjson.JSON;

import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryDeteMthReq;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryParamPageReq;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Package com.gxjtkyy.service
 * @Author lizhenhua
 * @Date 2018/6/19 16:30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProStandServiceTest {


    @Autowired
    private ProStandService proStandService;

    @Test
    public void getParamList() throws Exception {
        QueryParamPageReq request = new QueryParamPageReq();
        request.setPageSize(10);
        request.setCurrentPage(1);
        request.setDocId("d6c6c2d49f6445708a2ab6c51cb12ee8");
        request.setTopCategory("传统型半甜黄酒");

        ResponseVO response = proStandService.getParamList(request);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void getDeteMth() throws Exception {
        QueryDeteMthReq request = new QueryDeteMthReq();
        request.setDocId("d6c6c2d49f6445708a2ab6c51cb12ee8");
        request.setDeteMth("总糖（第一法：廉爱农法）");
        ResponseVO response = proStandService.getDetailDeteMth(request);
        System.out.println(JSON.toJSONString(response));
    }

}