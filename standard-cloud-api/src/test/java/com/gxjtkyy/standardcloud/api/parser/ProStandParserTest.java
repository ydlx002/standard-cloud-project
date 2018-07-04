package com.gxjtkyy.standardcloud.api.parser;//package com.gxjtkyy.parser;
//
//import com.alibaba.fastjson.JSON;
//import com.gxjtkyy.domain.TemplateInfo;
//import com.gxjtkyy.domain.dto.MthStandDTO;
//import com.gxjtkyy.domain.dto.ProStandDTO;
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
//
//import static org.junit.Assert.*;
//
///**
// * @Package com.gxjtkyy.parser
// * @Author lizhenhua
// * @Date 2018/5/27 9:52
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class ProStandParserTest {
//
//    @Autowired
//    private TemplateService templateService;
//
//
//    @Test
//    public void doParse() throws Exception {
//        ProStandParser parser = new ProStandParser();
//        ProStandDTO dto = parser.doParse("F:\\doctemp\\GBT 13662-2008~黄酒~标准库.demo.xlsx");
//        System.out.println("-------->"+ JSON.toJSONString(dto));
//    }
//
//    @Test
//    public void doParseT() throws Exception {
//
//        TemplateInfo templateInfo = templateService.getTemplateById("5b1f3f20e85866462866b5e3");
//        ProStandParser parser = new ProStandParser();
//        Workbook wb = parser.preParse("F:\\doctemp\\pro_standard.xlsx", templateInfo);
//        @Cleanup FileOutputStream os = new FileOutputStream("F:\\doctemp\\pro_standard_update.xlsx");
//         wb.write(os);
//    }
//
//}