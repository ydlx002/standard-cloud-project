package com.gxjtkyy.standardcloud.api.parser;//package com.gxjtkyy.parser;
//
//import com.alibaba.fastjson.JSON;
//import com.gxjtkyy.domain.TemplateInfo;
//import com.gxjtkyy.service.TemplateService;
//import lombok.Cleanup;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.FileOutputStream;
//import java.util.Map;
//
//import static org.junit.Assert.*;
//
///**
// * @Package com.gxjtkyy.parser
// * @Author lizhenhua
// * @Date 2018/6/12 14:34
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class ExcelParserTest {
//
//
//    @Autowired
//    private TemplateService templateService;
//
//    @Test
//    public void preParse() throws Exception {
//
//    }
//
//    @Test
//    public void doParse() throws Exception {
//        TemplateInfo templateInfo = templateService.getTemplateById("5b1f3f20e85866462866b5e3");
//        ProStandParser parser = new ProStandParser();
//        Workbook wb = parser.preParse("F:\\doctemp\\GBT 13662-2008~黄酒~标准库.demo.xlsx", templateInfo);
//        Map map = parser.doParse(wb);
//        System.out.println(JSON.toJSONString(map));
//    }
//
//}