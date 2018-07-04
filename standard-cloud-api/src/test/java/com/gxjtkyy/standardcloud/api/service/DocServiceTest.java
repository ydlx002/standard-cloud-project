package com.gxjtkyy.standardcloud.api.service;

import com.alibaba.fastjson.JSON;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.api.service.DocService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Package com.gxjtkyy.service
 * @Author lizhenhua
 * @Date 2018/5/29 14:47
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DocServiceTest {

//    @Autowired
//    private DocService docService;
//
//    @Test
//    public void addDoc() throws Exception {
//        docService.addDoc("11aefb78bca142a0896c08fbefa2ddcf","F:\\doctemp\\new\\GBT 13662-2008~黄酒~标准库.xlsx");
//    }
//
//    @Test
//    public void addDoc2() throws Exception {
//        docService.addDoc("71f1b18226744d5691ac0c3c66b9aab9","F:\\doctemp\\2018版抽样细则~淀粉及淀粉制品~淀粉糖.xlsx");
//    }
//
//    @Test
//    public void addDoc3() throws Exception {
//        docService.addDoc("8afbe9ade7a34d2bb2399f71f4b22f0a","F:\\doctemp\\GB 2760-2014~食品安全国家标准 食品添加剂使用标准~二氧化硅.xlsx");
//    }


//    @Test
//    public void searchDocs() throws Exception {
//        ResponseVO response = docService.searchDocs(null);
//        System.out.println(JSON.toJSONString(response));
//    }

    @Test
    public void testPageProStand() throws Exception {
        //DetailDocDTO dto = (DetailDocDTO) docService.getDocById("3fa0f19276ea4ba3b5e918913de7acb2").getData();
//        Map<String, Object> map = (Map<String, Object>)dto.getContent();
//        for (Iterator i = map.keySet().iterator(); i.hasNext();) {
//            String key = (String)i.next();
//            Object value = map.get(key);
//            if(value instanceof List){
//                List list = (List)value;
//                int count = list.size();
//                Page page = new Page();
//                page.setCount(count);
//                page.setPageSize(10);
//                page.setCurrentPage(1);
//                page.setDataList(RAMPageUtil.page(list, 1));
//                map.put(key, page);
//            }
//        }
//        System.out.println(JSON.toJSONString(map));
    }

}