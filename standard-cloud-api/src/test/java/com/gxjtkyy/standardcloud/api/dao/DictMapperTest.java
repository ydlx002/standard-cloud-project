package com.gxjtkyy.standardcloud.api.dao;//package com.gxjtkyy.dao;
//
//import com.gxjtkyy.domain.dto.DictDTO;
//import com.gxjtkyy.domain.dto.MthStandDTO;
//import com.gxjtkyy.parser.ExcelParser;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.omg.CORBA.PRIVATE_MEMBER;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//
//import java.util.Date;
//import java.util.Iterator;
//import java.util.Map;
//
//import static org.junit.Assert.*;
//
///**
// * @Package com.gxjtkyy.dao
// * @Author lizhenhua
// * @Date 2018/5/30 9:04
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class DictMapperTest extends ExcelParser<MthStandDTO>{
//
//    @Resource
//    private DictComMapper dictMapper;
//
//    @Test
//    public void insert() throws Exception {
//        Map<String, String> map = this.getDictInfo();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            String key = entry.getKey().toString();
//            String value = entry.getValue().toString();
//            System.out.println("key=" + key + " value=" + value);
//            DictDTO dto = new DictDTO();
//            dto.setDictCode(value);
//            dto.setDictName(key);
//            dto.setDictType(1);
//            dto.setDictDesc(key);
//            dto.setCreateTime(new Date());
//            dto.setUpdateTime(new Date());
//            dto.setOperator("1");
//            dictMapper.insert(dto);
//        }
//    }
//
//    @Override
//    public Integer getTemplateType() {
//        return null;
//    }
//
//    @Override
//    public MthStandDTO doParse(String path) throws Exception {
//        return null;
//    }
//}