package com.gxjtkyy.standardcloud.api.service;

import com.alibaba.fastjson.JSON;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryDeteMthPageReq;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryDeteMthReq;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Package com.gxjtkyy.service
 * @Author lizhenhua
 * @Date 2018/6/21 9:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MthStandServiceTest {



    @Autowired
    private MthStandService mthStandService;


    @Test
    public void getDeteMthList() throws Exception {
        QueryDeteMthPageReq request = new QueryDeteMthPageReq();
        request.setDocId("4f808a5b700d48fb9896d07f5bbaf8ba");
        ResponseVO response = mthStandService.getDeteMthList(request);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void getDetailDeteMth() throws Exception {
        QueryDeteMthReq request = new QueryDeteMthReq();
        request.setDocId("4f808a5b700d48fb9896d07f5bbaf8ba");
        request.setDeteMth("食品中铜（第一法：石墨炉原子吸收光谱法）");
        ResponseVO response = mthStandService.getDetailDeteMth(request);
        System.out.println(JSON.toJSONString(response));
    }

}