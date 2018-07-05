package com.gxjtkyy.standardcloud.admin.controller;

import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryDocPageReq;
import com.gxjtkyy.standardcloud.admin.service.DocService;
import com.gxjtkyy.standardcloud.common.annotation.ApiAction;
import com.gxjtkyy.standardcloud.common.constant.ResultCode;
import com.gxjtkyy.standardcloud.common.domain.vo.DocRequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.exception.DocException;
import com.gxjtkyy.standardcloud.common.exception.TemplateException;
import com.gxjtkyy.standardcloud.common.utils.BusiUtil;
import com.gxjtkyy.standardcloud.common.utils.DateUtils;
import com.gxjtkyy.standardcloud.common.utils.ZipUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static com.gxjtkyy.standardcloud.common.constant.ResultCode.*;

/**
 * @Package com.gxjtkyy.standardcloud.admin.controller
 * @Author lizhenhua
 * @Date 2018/6/28 15:15
 */
@Slf4j
@RestController
@RequestMapping("/admin/doc")
public class DocController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private DocService docService;

    /**
     * 添加文档
     * 支持压缩包上传以及单表格上传，其中压缩包可同时包含多个文档
     * @param file  文档数据流
     * @param templateId 模板ID
     * @return
     * @throws BaseException
     */
    @ApiOperation(value = "添加文档", notes = "添加文档")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "登录凭证", required = false, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "file", value = "文档", required = true, dataType = "MultipartFile", paramType = "form"),
            @ApiImplicitParam(name = "templateId", value = "模板ID", required = true, dataType = "String", paramType = "form")
    })
    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseVO add(@RequestParam("file") MultipartFile file, @RequestParam("templateId") String templateId) throws BaseException {
        BusiUtil.setLogIndex(UUID.randomUUID().toString().replace("-",""));
        ResponseVO response = new ResponseVO();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String originName = fileName.substring(0, fileName.lastIndexOf("."));
        if (!suffix.equals("zip") && !suffix.equals("xls") && !suffix.equals("xlsx")) {
            throw new TemplateException(RESULT_CODE_1013, RESULT_DESC_1013);
        }
        if (StringUtils.isEmpty(templateId)) {
            throw new TemplateException(RESULT_CODE_1012, RESULT_DESC_1012);
        }
        try {
            // ----->   upload/doc/20180630
            final String basePath = uploadPath + File.separator + "doc" + File.separator + DateUtils.getStringDateClear();
            File sfile = new File(basePath);
            if (!sfile.exists()) {
                sfile.mkdirs();
            }
            //  upload/doc/20180630/04042d855a1348bda44f3c4416fffa17.zip
            String prefixName = UUID.randomUUID().toString().replace("-","");
            String newName = prefixName + "." + suffix;
            Files.copy(file.getInputStream(), Paths.get(basePath, newName));
            if("zip".equals(suffix)){
                ZipUtil.unzip(basePath + File.separator + newName, basePath + File.separator + prefixName);
                for (String fileItem : new File(basePath + File.separator + prefixName).list()) {
                    if (fileItem.endsWith(".xls") || fileItem.endsWith(".xlsx")) {
                        docService.addDoc("",templateId, basePath + File.separator +prefixName+ File.separator + fileItem);
                    }
                }
            }else{
                docService.addDoc(originName, templateId, basePath + File.separator + newName);
            }
        }catch (BaseException e){
            log.error("上传附件异常", e);
            response.setCode(e.getCode());
            response.setMsg(e.getMsg());
        }catch (Exception e) {
            log.error("上传附件异常", e);
            response.setCode(ResultCode.RESULT_CODE_9999);
            response.setMsg(ResultCode.RESULT_DESC_9999);
        }finally {
            BusiUtil.removeLogIndex();;
        }

        //返回json
        return response;
    }


    @ApiOperation(value="分页查询文档列表", notes="分页查询文档列表")
    @ApiImplicitParam(name = "request", value = "请求体", required = true, dataType = "QueryDocPageReq", paramType = "body")
    @ApiAction("分页查询文档列表")
    @PostMapping("/queryTable")
    public ResponseVO queryTable(@RequestBody QueryDocPageReq request) throws DocException {
        return docService.getListByPage(request);
    }


    @ApiOperation(value="删除指定文档", notes="删除指定文档")
    @ApiImplicitParam(name = "request", value = "请求体", required = true, dataType = "DocRequestVO", paramType = "body")
    @ApiAction("删除指定文档")
    @PostMapping("/delete")
    public ResponseVO delete(@RequestBody DocRequestVO request) throws DocException {
        return docService.deleteDoc(request);
    }

}
