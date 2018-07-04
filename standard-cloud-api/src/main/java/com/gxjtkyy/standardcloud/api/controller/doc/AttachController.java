package com.gxjtkyy.standardcloud.api.controller.doc;


import com.gxjtkyy.standardcloud.api.service.DocService;
import com.gxjtkyy.standardcloud.api.service.StandDocService;
import com.gxjtkyy.standardcloud.common.annotation.ApiAction;
import com.gxjtkyy.standardcloud.common.domain.info.AttachInfo;
import com.gxjtkyy.standardcloud.common.domain.vo.DocRequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.constant.ResultCode;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.exception.TemplateException;
import com.gxjtkyy.standardcloud.common.utils.DateUtils;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.gxjtkyy.standardcloud.common.constant.ResultCode.*;

/**
 * 附件控制器
 *
 * @Package com.gxjtkyy.controller
 * @Author lizhenhua
 * @Date 2018/6/19 20:29
 */
@Slf4j
@RestController
@RequestMapping("/doc/attach")
public class AttachController {


    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private DocService docService;

    @Autowired
    private StandDocService standDocService;


    @ApiOperation(value="获取附录", notes="获取附录")
    @ApiImplicitParam(name = "request", value = "请求体", required = true, dataType = "DocRequestVO", paramType = "body")
    @PostMapping("/getAttachDoc")
    @ApiAction("获取附录")
    public ResponseVO getAttachDoc(@RequestBody DocRequestVO request) throws BaseException{
        return standDocService.getAttachDoc(request.getDocId());
    }


    /**
     * 处理文件上传
     *
     * @param file
     * @return
     */
    @ApiOperation(value = "添加附录", notes = "添加附录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "登录凭证", required = false, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "file", value = "excel数据流", required = true, dataType = "MultipartFile", paramType = "form"),
            @ApiImplicitParam(name = "docId", value = "文档Id", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "docType", value = "文档类型", required = false, dataType = "Integer", paramType = "form")
    })
    @PostMapping(value = "/upload")
    @ResponseBody
    public ResponseVO upload(@RequestParam("file") MultipartFile file, String docId, Integer docType) throws BaseException {
        ResponseVO response = new ResponseVO();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String tempName = fileName.substring(0, fileName.lastIndexOf("."));
        if (!suffix.equals("jpg") && !suffix.equals("bmp") && !suffix.equals("png") && !suffix.equals("gif") && !suffix.equals("jpeg")) {
            throw new TemplateException(RESULT_CODE_1013, RESULT_DESC_1013);
        }
        if (StringUtils.isEmpty(docId)) {
            throw new TemplateException(RESULT_CODE_1009, RESULT_DESC_1009);
        }
        if (null == docType) {
            docType = docService.getDocType(docId);
        }
        try {
            String dataStr = DateUtils.getStringDateClear();
            String savePath = uploadPath + File.separator + "attach" + File.separator + dataStr;
            File sfile = new File(savePath);
            if (!sfile.exists()) {
                sfile.mkdirs();
            }
            String attachId = UUID.randomUUID().toString().replace("-", "");
            Files.copy(file.getInputStream(), Paths.get(savePath, attachId+"."+suffix ));

            AttachInfo attachInfo = new AttachInfo();
            attachInfo.setId(attachId);
            attachInfo.setAttachName(tempName);
            attachInfo.setUrl("/" + dataStr + "/" + attachId+"."+suffix);
            return standDocService.addDocAttach(docId, docType, "attach", attachInfo);
        }catch (BaseException e){
            log.error("上传附件异常", e);
            response.setCode(e.getCode());
            response.setMsg(e.getMsg());
        }catch (IOException e) {
            log.error("上传附件异常", e);
            response.setCode(ResultCode.RESULT_CODE_9999);
            response.setMsg(ResultCode.RESULT_DESC_9999);
        }
        //返回json
        return response;
    }

}
