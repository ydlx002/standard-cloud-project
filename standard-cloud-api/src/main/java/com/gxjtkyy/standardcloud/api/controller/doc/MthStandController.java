package com.gxjtkyy.standardcloud.api.controller.doc;

import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryDeteMthPageReq;
import com.gxjtkyy.standardcloud.common.annotation.ApiAction;
import com.gxjtkyy.standardcloud.common.domain.vo.DocRequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryDeteMthReq;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryReferDocReq;
import com.gxjtkyy.standardcloud.api.service.MthStandService;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 方法标准控制器
 * @Package com.gxjtkyy.controller
 * @Author lizhenhua
 * @Date 2018/6/21 8:41
 */
@Slf4j
@RequestMapping("/doc/mth")
@RestController
public class MthStandController {

    @Autowired
    private MthStandService mthStandService;


    @ApiOperation(value="查询方法标准详情--文本文档", notes="查询方法标准详情--文本文档")
    @ApiImplicitParam(name = "request", value = "请求体", required = true, dataType = "RequestVO", paramType = "body")
    @PostMapping("/getTextDoc")
    @ApiAction("查询方法标准详情")
    public  ResponseVO getTextDoc(@RequestBody DocRequestVO request) throws BaseException {
        return mthStandService.getTextDoc(request.getDocId());
    }


    @ApiOperation(value="获取方法详情列表", notes="获取方法详情列表")
    @ApiImplicitParam(name = "request", value = "请求体", dataType = "QueryParamPageReq", required = true, paramType = "body")
    @ApiAction("获取方法详情列表")
    @PostMapping("/getDeteMthList")
    public ResponseVO getDeteMthList(@RequestBody QueryDeteMthPageReq request) throws BaseException{
        return mthStandService.getDeteMthList(request);
    }


    @ApiOperation(value="获取检测方法详情", notes="获取检测方法详情")
    @ApiImplicitParam(name = "request", value = "请求体", required = true,dataType = "QueryDeteMthReq", paramType = "body")
    @ApiAction("获取检测方法详情")
    @PostMapping("/getDeteMth")
    public ResponseVO getDeteMth(@RequestBody QueryDeteMthReq request) throws BaseException{
        return mthStandService.getDetailDeteMth(request);
    }


    @ApiOperation(value="获取相关产品", notes="获取相关产品")
    @ApiImplicitParam(name = "request", value = "请求体", required = true,dataType = "QueryReferDocReq", paramType = "body")
    @ApiAction("获取相关产品")
    @PostMapping("/getReferDoc")
    public ResponseVO getReferDoc(@RequestBody QueryReferDocReq request) throws BaseException{
        return mthStandService.getReferDoc(request);
    }

}
