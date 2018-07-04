package com.gxjtkyy.standardcloud.admin.controller;


import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryTemplatePageReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryTemplateReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.UpdateTemplateReq;
import com.gxjtkyy.standardcloud.admin.service.TemplateService;
import com.gxjtkyy.standardcloud.common.annotation.ApiAction;
import com.gxjtkyy.standardcloud.common.constant.ResultCode;
import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.TemplateException;
import com.gxjtkyy.standardcloud.common.utils.BusiUtil;
import com.gxjtkyy.standardcloud.common.utils.DateUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static com.gxjtkyy.standardcloud.common.constant.ResultCode.*;

/**
 * 模板控制器
 * @Package com.gxjtkyy.controller
 * @Author lizhenhua
 * @Date 2018/6/19 20:29
 */
@Slf4j
@RestController
@RequestMapping("/admin/template")
public class TemplateController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private TemplateService templateService;

    /**
     * 处理文件上传
     * @param file
     * @return
     */
    @ApiOperation(value="添加文档模板", notes="添加文档模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "登录凭证", required = false, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "file", value = "excel数据流", required = true, dataType = "MultipartFile", paramType = "form"),
            @ApiImplicitParam(name = "docType", value = "文档类型", required = true, dataType = "Integer", paramType = "form")
    })
    @PostMapping(value="/add")
    public ResponseVO upload(@RequestParam("file") MultipartFile file, @RequestParam("docType") Integer docType)  throws TemplateException{
        BusiUtil.setLogIndex(UUID.randomUUID().toString().replace("-",""));
        ResponseVO response = new ResponseVO();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        String tempName = fileName.substring(0, fileName.lastIndexOf("."));
        if (!suffix.equals("xls") && !suffix.equals("xlsx")){
            throw new TemplateException(RESULT_CODE_1013, RESULT_DESC_1013);
        }
        if(null == docType){
            throw new TemplateException(RESULT_CODE_1014, RESULT_DESC_1014);
        }
        try {
            String savePath = uploadPath+ File.separator + "template" +File.separator + DateUtils.getStringDateClear();
            File sfile = new File(savePath);
            if(!sfile.exists()){
                sfile.mkdirs();
            }
            String newName = tempName+"_"+System.currentTimeMillis()+"."+suffix;
            Files.copy(file.getInputStream(), Paths.get(savePath, newName));
            templateService.add(tempName, docType, savePath + File.separator + newName);
        } catch (IOException e) {
            log.error("上传异常", e);
            response.setCode(ResultCode.RESULT_CODE_9999);
            response.setMsg(ResultCode.RESULT_DESC_9999);
        }finally {
            BusiUtil.removeLogIndex();
        }
        //返回json
        return response;
    }


    @ApiOperation(value="分页查询文档模板列表", notes="分页查询文档模板列表")
    @ApiImplicitParam(name = "request", value = "请求体", required = true, dataType = "QueryTemplatePageReq", paramType = "body")
    @ApiAction("分页查询文档模板列表")
    @PostMapping("/queryTable")
    public ResponseVO queryTable(@RequestBody QueryTemplatePageReq request) throws TemplateException{
        return templateService.getListByPage(request);
    }


    @ApiOperation(value="获取所有文档模板", notes="获取所有文档模板")
    @ApiAction("获取所有文档模板")
    @PostMapping("/getAll")
    public ResponseVO getAll(@RequestBody RequestVO request) throws TemplateException{
        return templateService.getAllTemplate();
    }

    @ApiOperation(value="获取模板详情", notes="获取模板详情")
    @ApiAction("获取模板详情")
    @PostMapping("/getTemplate")
    public ResponseVO getTemplate(@RequestBody QueryTemplateReq request) throws TemplateException{
        return templateService.getTemplateDetail(request);
    }

    @ApiOperation(value="更新模板", notes="更新模板")
    @ApiAction("更新模板")
    @PostMapping("/update")
    public ResponseVO update(@RequestBody UpdateTemplateReq request) throws TemplateException{
        return templateService.updateTemplate(request);
    }

}
