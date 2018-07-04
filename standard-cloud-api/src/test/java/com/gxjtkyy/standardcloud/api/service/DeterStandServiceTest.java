package com.gxjtkyy.standardcloud.api.service;

import com.alibaba.fastjson.JSON;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.api.service.DeterStandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Package com.gxjtkyy.service
 * @Author lizhenhua
 * @Date 2018/6/26 14:33
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DeterStandServiceTest {


    @Autowired
    private DeterStandService deterStandService;


    @Test
    public void getDocsByMthNo() throws Exception {
        ResponseVO response = deterStandService.getDocsByMthNo("testst",1,10);
        System.out.println(JSON.toJSONString(response));
    }

}