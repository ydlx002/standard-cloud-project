package com.gxjtkyy.standardcloud.api.service.impl;


import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryDeteMthReq;
import com.gxjtkyy.standardcloud.common.constant.DocConstant;
import com.gxjtkyy.standardcloud.common.domain.dto.TemplateDTO;
import com.gxjtkyy.standardcloud.common.domain.info.AttachInfo;
import com.gxjtkyy.standardcloud.common.domain.info.SheetInfo;
import com.gxjtkyy.standardcloud.common.domain.dto.BriefDocDTO;
import com.gxjtkyy.standardcloud.common.domain.vo.AttachVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.api.service.DocService;
import com.gxjtkyy.standardcloud.api.service.StandDocService;
import com.gxjtkyy.standardcloud.common.constant.DocTemplate;
import com.gxjtkyy.standardcloud.common.constant.ResultCode;
import com.gxjtkyy.standardcloud.common.constant.TemplateConstant;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.exception.DocException;
import com.gxjtkyy.standardcloud.common.exception.TemplateException;
import com.mongodb.WriteResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import static com.gxjtkyy.standardcloud.common.constant.ResultCode.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

/**
 * 标准文档接口服务实现层
 *
 * @Package com.gxjtkyy.service.impl
 * @Author lizhenhua
 * @Date 2018/6/16 16:14
 */
@Slf4j
@Service("standDocService")
public class StandDocServiceImpl implements StandDocService {

    @Autowired
    private DocService docService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public TemplateDTO getTemplateById(String id) throws TemplateException {
        Criteria criteria = new Criteria("_id");
        criteria.is(id);
        Query query = new Query(criteria);
        TemplateDTO template = mongoTemplate.findOne(query, TemplateDTO.class, DocConstant.COLLECTION_DOC_TEMPLATE_CATALOG);
        if(null == template){
            log.error("模板不存在 --> id:" + id);
            throw new TemplateException(RESULT_CODE_1001, String.format(RESULT_DESC_1001, id));
        }
        return template;
    }

    @Override
    public ResponseVO getTextDoc(String docId) throws BaseException {
        ResponseVO response = new ResponseVO();
        BriefDocDTO briefDoc = docService.getBriefDoc(docId);
        TemplateDTO template = this.getTemplateById(briefDoc.getTemplateId());
        Map<String, Object> docMap = (Map<String, Object>) docService.getDoc(docId, briefDoc.getDocType()).getContent();
        Map<String, Object> data = new LinkedHashMap<>();

        for (SheetInfo sheetInfo : template.getCatalog()) {
            if (sheetInfo.getDataModel().equals(TemplateConstant.DATA_MODEL_TEXT)) {
                String key = sheetInfo.getSheetCode();
                data.put(key, docMap.get(key));
            }
        }
        response.setData(data);
        return response;
    }

    @Override
    public ResponseVO getTableDoc(String docId) throws BaseException {
        ResponseVO response = new ResponseVO();
        BriefDocDTO briefDoc = docService.getBriefDoc(docId);
        TemplateDTO template = this.getTemplateById(briefDoc.getTemplateId());
        Map<String, Object> docMap = (Map<String, Object>) docService.getDoc(docId, briefDoc.getDocType()).getContent();
        Map<String, Object> data = new LinkedHashMap<>();

        for (SheetInfo sheetInfo : template.getCatalog()) {
            if (sheetInfo.getDataModel().equals(TemplateConstant.DATA_MODEL_TABLE)) {
                String key = sheetInfo.getSheetCode();
                data.put(key, docMap.get(key));
            }
        }
        response.setData(data);
        return response;
    }

    @Override
    public ResponseVO getAttachDoc(String docId) throws BaseException {
        ResponseVO response = new ResponseVO();
        BriefDocDTO briefDoc = docService.getBriefDoc(docId);
        TemplateDTO template = this.getTemplateById(briefDoc.getTemplateId());
        Map<String, Object> docMap = (Map<String, Object>) docService.getDoc(docId, briefDoc.getDocType()).getContent();
        Map<String, Object> data = new LinkedHashMap<>();

        for (SheetInfo sheetInfo : template.getCatalog()) {
            if (sheetInfo.getDataModel().equals(TemplateConstant.DATA_MODEL_ATTACH)) {
                String key = sheetInfo.getSheetCode();
                data.put(key, docMap.get(key));
            }
        }
        response.setData(data);
        return response;
    }

    @Override
    public ResponseVO getNavigation(String docId) throws BaseException {
        ResponseVO response = new ResponseVO();
        BriefDocDTO briefDoc = docService.getBriefDoc(docId);
        TemplateDTO template = this.getTemplateById(briefDoc.getTemplateId());
        response.setData(template.getNavigation());
        return response;
    }

    @Override
    public ResponseVO addDocAttach(String docId, Integer docType, String node, Object object) throws BaseException {
        if (null == docId) {
            throw new DocException(RESULT_CODE_1010, RESULT_DESC_1010);
        }
        if (null == docType) {
            docType = docService.getDocType(docId);
        }
        Query query = Query.query(Criteria.where("_id").is(docId));
        Update update = new Update();
        update.addToSet("content." + node + "." + node, object);
        ResponseVO response = new ResponseVO();
        try {
            WriteResult result = mongoTemplate.upsert(query, update, DocTemplate.getTemplateByType(docType).getTableName());
            if (result.getN() > 0) {
                return new ResponseVO();
            }
        } catch (DocException e) {
            log.error("更新文档失败 --> {}", e);
        }
        response.setCode(ResultCode.RESULT_CODE_9999);
        response.setMsg(ResultCode.RESULT_DESC_9999);
        return response;
    }

    @Override
    public List<Object> getContentDoc(String docId, Integer docType, String node, String key, String value) throws BaseException {
        if (null == docId) {
            throw new DocException(RESULT_CODE_1010, RESULT_DESC_1010);
        }
        if (null == docType) {
            docType = docService.getDocType(docId);
        }

        List<AggregationOperation> commonOperations = new ArrayList<>(9);
        commonOperations.add(Aggregation.match(Criteria.where("_id").is(docId)));
        commonOperations.add(project("content." + node).andExclude("_id"));
        commonOperations.add(unwind(node));
        //查询条件
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            commonOperations.add(Aggregation.match(Criteria.where(node + "." + key).is(value)));
        }
        DocTemplate docTemplate = DocTemplate.getTemplateByType(docType);
        List<Map> mapList = mongoTemplate.aggregate(Aggregation.newAggregation(commonOperations), docTemplate.getTableName(), Map.class).getMappedResults();
        List<Object> forMap = new ArrayList<>();
        for (Map map : mapList) {
            forMap.add(map.get(node));
        }
        return forMap;
    }

}
