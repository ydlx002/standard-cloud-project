package com.gxjtkyy.standardcloud.admin.service.impl;

import com.gxjtkyy.standardcloud.admin.domain.vo.DocVO;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryDocPageReq;
import com.gxjtkyy.standardcloud.admin.service.DocService;
import com.gxjtkyy.standardcloud.admin.service.TemplateService;
import com.gxjtkyy.standardcloud.common.constant.DocConstant;
import com.gxjtkyy.standardcloud.common.constant.DocTemplate;
import com.gxjtkyy.standardcloud.common.domain.Page;
import com.gxjtkyy.standardcloud.common.domain.dto.BriefDocDTO;
import com.gxjtkyy.standardcloud.common.domain.dto.DetailDocDTO;
import com.gxjtkyy.standardcloud.common.domain.dto.TemplateDTO;
import com.gxjtkyy.standardcloud.common.domain.vo.DocRequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.exception.DocException;
import com.gxjtkyy.standardcloud.common.parser.StandDocParser;
import com.mongodb.WriteResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.gxjtkyy.standardcloud.common.constant.ResultCode.*;

/**
 * @Package com.gxjtkyy.standardcloud.admin.service.impl
 * @Author lizhenhua
 * @Date 2018/6/28 8:22
 */
@Slf4j
@Service
public class DocServiceImpl implements DocService{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TemplateService templateService;


    @Override
    public void addDoc(String name, String templateId, String docPath) throws BaseException {
        //查询模板是否存在
        TemplateDTO template = templateService.getTemplateById(templateId);
        DetailDocDTO detailDoc = new StandDocParser(template, docPath).doParse(name);
        String tableName = DocTemplate.getTemplateByType(detailDoc.getDocType()).getTableName();
        BriefDocDTO briefDoc = new BriefDocDTO(detailDoc);
        mongoTemplate.insert(detailDoc, tableName);
        mongoTemplate.insert(briefDoc, DocConstant.COLLECTION_DOC_STAND_CATALOG);
    }


    @Override
    public ResponseVO getListByPage(QueryDocPageReq request) throws DocException {
        ResponseVO response = new ResponseVO();
        Page<DocVO> page = new Page<>();
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(request.getDocId())){
            criteria.and("_id").is(request.getDocId());
        }
        if(!StringUtils.isEmpty(request.getDocName())){
            criteria.and("docName").regex(request.getDocName());
        }
        if(null != request.getDocType()){
            criteria.and("docType").is(request.getDocType());
        }
        Query query = new Query(criteria);
        //查询总数
        int count = (int) mongoTemplate.count(query, BriefDocDTO.class, DocConstant.COLLECTION_DOC_STAND_CATALOG);
        page.setCount(count);

        //排序
        query.with(new Sort(Sort.Direction.DESC, "viewCount"));
        int start = (request.getCurrentPage() - 1) * request.getPageSize();
        query.skip(start).limit(request.getPageSize());
        //skip方法是跳过条数，而且是一条一条的跳过，如果集合比较大时（如书页数很多）skip会越来越慢, 需要更多的处理器(CPU)，这会影响性能。后续升级改用Morphia框架
        List<BriefDocDTO> list = mongoTemplate.find(query, BriefDocDTO.class, DocConstant.COLLECTION_DOC_STAND_CATALOG);
        List<DocVO> docVOS = new ArrayList<>();
        for(BriefDocDTO briefDoc : list){
            DocVO vo = new DocVO();
            vo.setDocType(briefDoc.getDocType());
            vo.setCreateTime(briefDoc.getCreateTime());
            vo.setDocId(briefDoc.getDocId());
            vo.setDocName(briefDoc.getDocName());
            docVOS.add(vo);
        }
        page.setCurrentPage(request.getCurrentPage());
        page.setPageSize(request.getPageSize());
        page.setDataList(docVOS);
        response.setData(page);
        return response;

    }

    @Override
    public ResponseVO deleteDoc(DocRequestVO request) throws DocException {
        Criteria criteria = new Criteria("_id");
        criteria.is(request.getDocId());
        Query query = new Query(criteria);
        BriefDocDTO briefDoc = mongoTemplate.findOne(query, BriefDocDTO.class, DocConstant.COLLECTION_DOC_STAND_CATALOG);
        if (null == briefDoc) {
            log.error("摘要表中查询不到文档 --> id: {}", request.getDocId());
            throw new DocException(RESULT_CODE_1007, RESULT_DESC_1007);
        }

        new Criteria("_id").is(request.getDocId());
        WriteResult result = mongoTemplate.remove(new Query(criteria), DocTemplate.getTemplateByType(briefDoc.getDocType()).getTableName());
        if(result.getN() > 0){
            mongoTemplate.remove(new Query(criteria),DocConstant.COLLECTION_DOC_STAND_CATALOG);
            return new ResponseVO();
        }
        ResponseVO response = new ResponseVO();
        response.setCode(RESULT_CODE_9999);
        response.setMsg(RESULT_DESC_9999);
        return response;
    }
}
