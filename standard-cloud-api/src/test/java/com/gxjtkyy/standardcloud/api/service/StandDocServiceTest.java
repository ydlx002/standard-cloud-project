package com.gxjtkyy.standardcloud.api.service;

import com.alibaba.fastjson.JSON;

import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryDeteMthReq;
import com.gxjtkyy.standardcloud.common.domain.info.AttachInfo;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.api.service.ProStandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @Package com.gxjtkyy.service
 * @Author lizhenhua
 * @Date 2018/6/16 15:43
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StandDocServiceTest {



    @Test
    public void getDetailDeteMth() throws Exception {
        QueryDeteMthReq request = new QueryDeteMthReq();
        request.setDocId("698302c2450a4009a892679780ea31b0");
        request.setDeteBasis("GBT 13662-2008");
        request.setDeteItem("总糖（以葡萄糖计）");
        request.setDocType(0);
        request.setDeteMth("廉爱农法");
        ResponseVO response = proStandService.getDetailDeteMth(request);
        System.out.println(JSON.toJSONString(response));
    }


//    @Autowired
//    private StandDocService standDocService;

    @Autowired
    private ProStandService proStandService;


    @Test
    public void getTextDoc() throws Exception {
        ResponseVO response = proStandService.getTextDoc("d2ca666ed1ee49c4b77ef4f96e9f1c90");
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void getNavigation() throws Exception {
        ResponseVO response = proStandService.getNavigation("d2ca666ed1ee49c4b77ef4f96e9f1c90");
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void updateDocAttach() throws Exception {
        AttachInfo attachInfo = new AttachInfo();
        attachInfo.setAttachName("测试");
        attachInfo.setId(UUID.randomUUID().toString().replace("-",""));
        attachInfo.setUrl("/path/123.jpg");
        ResponseVO response = proStandService.addDocAttach("605011e51ca848fc91e5b68f63bd9e2f",0,"content.attach",attachInfo);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void getAttachDoc() throws Exception {
        ResponseVO response = proStandService.getAttachDoc("605011e51ca848fc91e5b68f63bd9e2f");
        System.out.println(JSON.toJSONString(response));
    }

}