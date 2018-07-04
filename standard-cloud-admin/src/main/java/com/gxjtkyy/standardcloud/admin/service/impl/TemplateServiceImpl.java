package com.gxjtkyy.standardcloud.admin.service.impl;

import com.gxjtkyy.standardcloud.admin.domain.vo.TemplateVO;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryTemplatePageReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryTemplateReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.UpdateTemplateReq;
import com.gxjtkyy.standardcloud.admin.service.TemplateService;
import com.gxjtkyy.standardcloud.common.constant.DocConstant;
import com.gxjtkyy.standardcloud.common.constant.TemplateConstant;
import com.gxjtkyy.standardcloud.common.domain.Page;
import com.gxjtkyy.standardcloud.common.domain.dto.TemplateDTO;
import com.gxjtkyy.standardcloud.common.domain.info.SheetInfo;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.TemplateException;
import com.gxjtkyy.standardcloud.common.utils.BusiUtil;
import com.gxjtkyy.standardcloud.common.utils.DictCacheUtil;
import com.gxjtkyy.standardcloud.common.utils.POIUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

import static com.gxjtkyy.standardcloud.common.constant.ResultCode.*;

/**
 * 模板服务实现
 *
 * @Package com.gxjtkyy.standardcloud.admin.service
 * @Author lizhenhua
 * @Date 2018/6/27 12:00
 */
@Slf4j
@Service
public class TemplateServiceImpl implements TemplateService {


    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void add(String templateName, int docType, String path) throws TemplateException {
        File file = new File(path);
        Workbook wb = POIUtil.getWBFormExcel(file);
        if (null != wb) {
            TemplateDTO template = new TemplateDTO();
            template.setDocType(docType);
            template.setTemplateName(templateName);
            template.setCreateTime(new Date());
            template.setUpdateTime(new Date());
            Iterator<Sheet> sheets = wb.sheetIterator();
            DictCacheUtil dictCacheUtil = DictCacheUtil.getInstance();
            List<SheetInfo> catalog = new ArrayList<>();
            int sheetNum = 0;
            while (sheets.hasNext()) {
                Sheet sheet = sheets.next();
                SheetInfo sheetInfo = new SheetInfo();
                sheetInfo.setSheetCode(dictCacheUtil.getDictCode(sheet.getSheetName(), sheetNum, -1));
                int cols = sheet.getRow(0).getPhysicalNumberOfCells();
                sheetInfo.setSingleColumn(cols == 1);
                if (sheetInfo.isSingleColumn()) {
                    sheetInfo.setDataDirection(TemplateConstant.DATA_DIRECTION_VERTICAL);
                }
                List<String> colCodes = new ArrayList<>();
                for (int i = 0; i < cols; i++) {
                    Cell cell = sheet.getRow(0).getCell(i);
                    String value = POIUtil.getCellValue(cell).trim();
                    if (StringUtils.isEmpty(value)) {
                        break;
                    }
                    colCodes.add(dictCacheUtil.getDictCode(value, sheetNum, i));
                }

                //遍历行，获取起始行
                int rows = 0;
                for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                    if (null == sheet.getRow(j) || null == sheet.getRow(j).getCell(0) || StringUtils.isEmpty(POIUtil.getCellValue(sheet.getRow(j).getCell(0)))) {
                        break;
                    }
                    rows++;
                }
                sheetInfo.setStartRow(rows);
                sheetInfo.setColumns(colCodes);
                catalog.add(sheetInfo);
                sheetNum++;
                template.getNavigation().add(sheetInfo.getSheetCode());
            }
            template.setCatalog(catalog);
            template.setId(UUID.randomUUID().toString().replace("-", ""));
            mongoTemplate.save(template, DocConstant.COLLECTION_DOC_TEMPLATE_CATALOG);
        }
    }

    @Override
    public ResponseVO getListByPage(QueryTemplatePageReq request) throws TemplateException {
        ResponseVO response = new ResponseVO();
        Page<TemplateVO> page = new Page<>();
        Criteria criteria = new Criteria();
        if (!StringUtils.isEmpty(request.getTemplateName())) {
            criteria.and("templateName").regex(request.getTemplateName());
        }
        if (null != request.getDocType()) {
            criteria.and("docType").is(request.getDocType());
        }
        Query query = new Query(criteria);
        //查询总数
        int count = (int) mongoTemplate.count(query, TemplateDTO.class, DocConstant.COLLECTION_DOC_TEMPLATE_CATALOG);
        page.setCount(count);

        //排序
        int start = (page.getCurrentPage() - 1) * page.getPageSize();
        query.skip(start).limit(page.getPageSize());
        //skip方法是跳过条数，而且是一条一条的跳过，如果集合比较大时（如书页数很多）skip会越来越慢, 需要更多的处理器(CPU)，这会影响性能。后续升级改用Morphia框架
        List<TemplateDTO> list = mongoTemplate.find(query, TemplateDTO.class, DocConstant.COLLECTION_DOC_TEMPLATE_CATALOG);
        List<TemplateVO> vos = new ArrayList<>();
        for (TemplateDTO info : list) {
            TemplateVO vo = new TemplateVO();
            vo.setId(info.getId());
            vo.setTemplateName(info.getTemplateName());
            vo.setDocType(info.getDocType());
            vo.setCreateTime(info.getCreateTime());
            vo.setUpdateTime(info.getUpdateTime());
            vos.add(vo);
        }
        page.setDataList(vos);
        response.setData(page);
        return response;
    }

    @Override
    public ResponseVO getAllTemplate() throws TemplateException {
        ResponseVO response = new ResponseVO();
        List<TemplateDTO> list = mongoTemplate.find(null, TemplateDTO.class, DocConstant.COLLECTION_DOC_TEMPLATE_CATALOG);
        List<TemplateVO> vos = new ArrayList<>();
        for (TemplateDTO info : list) {
            TemplateVO vo = new TemplateVO();
            vo.setId(info.getId());
            vo.setTemplateName(info.getTemplateName());
            vo.setDocType(info.getDocType());
            vo.setCreateTime(info.getCreateTime());
            vo.setUpdateTime(info.getUpdateTime());
            vos.add(vo);
        }
        response.setData(vos);
        return response;
    }

    @Override
    public ResponseVO updateTemplate(UpdateTemplateReq request) throws TemplateException {
        if (StringUtils.isEmpty(request.getTemplateId())) {
            throw new TemplateException(RESULT_CODE_1012, RESULT_DESC_1012);
        }
        if (StringUtils.isEmpty(request.getNode())) {
            throw new TemplateException(RESULT_CODE_1017, RESULT_DESC_1017);
        }
        log.info(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), "更新模板:", request);
        Criteria criteria = new Criteria("_id").is(request.getTemplateId()).and("catalog.sheetCode").is(request.getNode());
        Update update = new Update();
        if (StringUtils.isEmpty(request.getDataModel()) && StringUtils.isEmpty(request.getDataDirection())) {
            throw new TemplateException(RESULT_CODE_1020, RESULT_DESC_1020);
        }
        if (!StringUtils.isEmpty(request.getDataDirection())) {
            update.set("catalog.$.dataDirection", request.getDataDirection());
            log.debug(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), "更新模板: " + request.getNode(), "dataDirection ->" + request.getDataDirection());
        }
        if (!StringUtils.isEmpty(request.getDataModel())) {
            update.set("catalog.$.dataModel", request.getDataModel());
            log.debug(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), "更新模板: " + request.getNode(), "dataModel ->" + request.getDataDirection());
        }
        ResponseVO response = new ResponseVO();
        try {
            mongoTemplate.updateFirst(new Query(criteria), update, DocConstant.COLLECTION_DOC_TEMPLATE_CATALOG);
        } catch (Exception e) {
            log.error(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), "更新模板", "异常", e);
            response.setCode(RESULT_CODE_9999);
            response.setMsg(RESULT_DESC_9999);
        }

        return response;
    }

    @Override
    public ResponseVO getTemplateDetail(QueryTemplateReq request) throws TemplateException {
        if (StringUtils.isEmpty(request.getTemplateId())) {
            throw new TemplateException(RESULT_CODE_1012, RESULT_DESC_1012);
        }
        TemplateDTO template = this.getTemplateById(request.getTemplateId());
        ResponseVO response = new ResponseVO();
        response.setData(template);
        return response;
    }

    @Override
    public TemplateDTO getTemplateById(String templateId) throws TemplateException {
        Criteria criteria = new Criteria("_id");
        criteria.is(templateId);
        Query query = new Query(criteria);
        TemplateDTO template = mongoTemplate.findOne(query, TemplateDTO.class, DocConstant.COLLECTION_DOC_TEMPLATE_CATALOG);
        if (null == template) {
            log.error(DocConstant.LOG_PRINT_FORMAT, "获取模板", "模板(" + templateId + ")不存在");
            throw new TemplateException(RESULT_CODE_1001, String.format(RESULT_DESC_1001, templateId));
        }
        return template;
    }
}
