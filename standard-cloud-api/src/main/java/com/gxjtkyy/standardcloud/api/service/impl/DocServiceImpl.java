package com.gxjtkyy.standardcloud.api.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.gxjtkyy.standardcloud.api.dao.DictMapper;
import com.gxjtkyy.standardcloud.api.domain.vo.DictVO;
import com.gxjtkyy.standardcloud.api.domain.vo.request.KeySearchReq;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryDictReq;
import com.gxjtkyy.standardcloud.common.domain.Page;
import com.gxjtkyy.standardcloud.common.domain.dto.BriefDocDTO;
import com.gxjtkyy.standardcloud.common.domain.dto.DetailDocDTO;
import com.gxjtkyy.standardcloud.common.domain.dto.DictDTO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.parser.StandDocParser;
import com.gxjtkyy.standardcloud.api.service.DocService;
import com.gxjtkyy.standardcloud.common.constant.DocConstant;
import com.gxjtkyy.standardcloud.common.constant.DocTemplate;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.exception.DocException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.gxjtkyy.standardcloud.common.constant.ResultCode.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 标准文档接口实现类
 *
 * @Package com.gxjtkyy.service.impl
 * @Author lizhenhua
 * @Date 2018/5/29 9:42
 */
@Service
@Slf4j
public class DocServiceImpl implements DocService {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public ResponseVO searchDocs(KeySearchReq request) throws BaseException{
        ResponseVO response = new ResponseVO();
        Page<BriefDocDTO> page = new Page<>();
        Criteria criteria = new Criteria("docName");
        criteria.regex(request.getKey());
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
        page.setPageSize(request.getPageSize());
        page.setCurrentPage(request.getCurrentPage());
        page.setDataList(list);
        response.setData(page);
        return response;
    }

    @Override
    public DetailDocDTO getDoc(String docId, Integer docType) throws DocException {
        if(StringUtils.isEmpty(docId)){
            throw new DocException(RESULT_CODE_1009, RESULT_DESC_1009);
        }
        Criteria criteria = new Criteria("_id");
        criteria.is(docId);
        Query query = new Query(criteria);
        if (null == docType) {
            BriefDocDTO briefDoc = mongoTemplate.findOne(query, BriefDocDTO.class, DocConstant.COLLECTION_DOC_STAND_CATALOG);
            if (null == briefDoc) {
                log.error("摘要表中查询不到文档 --> id: {}", docId);
                throw new DocException(RESULT_CODE_1007, RESULT_DESC_1007);
            }
            docType = briefDoc.getDocType();
        }
        DocTemplate docTemplate = DocTemplate.getTemplateByType(docType);
        return mongoTemplate.findOne(query, DetailDocDTO.class, docTemplate.getTableName());
    }

    @Override
    public Integer getDocType(String docId) throws DocException {
        Criteria criteria = new Criteria("_id");
        criteria.is(docId);
        Query query = new Query(criteria);
        BriefDocDTO briefDoc = mongoTemplate.findOne(query, BriefDocDTO.class, DocConstant.COLLECTION_DOC_STAND_CATALOG);
        if (null == briefDoc) {
            log.error("摘要表中查询不到文档 --> id: {}", docId);
            throw new DocException(RESULT_CODE_1007, RESULT_DESC_1007);
        }
        return briefDoc.getDocType();
    }

    @Override
    public String getTemplateId(String docId) throws DocException {
        Criteria criteria = new Criteria("_id");
        criteria.is(docId);
        Query query = new Query(criteria);
        BriefDocDTO briefDoc = mongoTemplate.findOne(query, BriefDocDTO.class, DocConstant.COLLECTION_DOC_STAND_CATALOG);
        if (null == briefDoc) {
            log.error("摘要表中查询不到文档 --> id: {}", docId);
            throw new DocException(RESULT_CODE_1007, RESULT_DESC_1007);
        }
        return briefDoc.getTemplateId();
    }

    @Override
    public BriefDocDTO getBriefDoc(String docId) throws DocException {
        Criteria criteria = new Criteria("_id");
        criteria.is(docId);
        Query query = new Query(criteria);
        BriefDocDTO briefDoc = mongoTemplate.findOne(query, BriefDocDTO.class, DocConstant.COLLECTION_DOC_STAND_CATALOG);
        if (null == briefDoc) {
            log.error("摘要表中查询不到文档 --> id: {}", docId);
            throw new DocException(RESULT_CODE_1007, RESULT_DESC_1007);
        }
        return briefDoc;
    }


    @Resource
    @Qualifier("DictMapper")
    private DictMapper dictMapper;


    @Override
    public ResponseVO getDicts(QueryDictReq request) {
        ResponseVO response = new ResponseVO();
        DictDTO query = new DictDTO();
        query.setDictCode(request.getDictCode());
        query.setDictName(request.getDictName());
        List<DictDTO> dictDTOS =  dictMapper.getList(query);
        List<DictVO> dictVOS = new ArrayList<>();
        for(DictDTO dto : dictDTOS){
            dictVOS.add(new DictVO(dto.getDictCode(), dto.getDictName()));
        }
        response.setData(dictVOS);
        return response;
    }
}
