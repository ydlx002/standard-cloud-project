package com.gxjtkyy.standardcloud.api.controller.doc;


import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryCategoryReq;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryDeteMthReq;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryParamPageReq;
import com.gxjtkyy.standardcloud.common.annotation.ApiAction;
import com.gxjtkyy.standardcloud.common.domain.vo.DocRequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.api.service.ProStandService;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品标准控制器
 * @Package com.gxjtkyy.controller
 * @Author lizhenhua
 * @Date 2018/6/19 11:13
 */
@RestController
@Slf4j
@RequestMapping("/doc/pro")
public class ProStandController {

    @Autowired
    private ProStandService proStandService;


    @ApiOperation(value="查询产品标准详情--文本文档", notes="查询产品标准详情--文本文档部分")
    @ApiImplicitParam(name = "request", value = "请求体", required = true, dataType = "RequestVO", paramType = "body")
    @ApiAction("查询产品标准详情")
    @PostMapping("/getTextDoc")
    public ResponseVO getTextDoc(@RequestBody DocRequestVO request) throws BaseException {
        return proStandService.getTextDoc(request.getDocId());
    }


    @ApiOperation(value="获取参数列表", notes="获取参数列表")
    @ApiImplicitParam(name = "request", value = "请求体", dataType = "QueryParamPageReq", required = true, paramType = "body")
    @ApiAction("获取参数列表")
    @PostMapping("/getParamList")
    public ResponseVO getParamList(@RequestBody QueryParamPageReq request) throws BaseException{
        return proStandService.getParamList(request);
    }


    @ApiOperation(value="获取产品分类列表", notes="获取产品分类列表")
    @ApiImplicitParam(name = "request", value = "请求体", required = true ,dataType = "QueryCategoryReq", paramType = "body")
    @ApiAction("获取产品分类列表")
    @PostMapping("/getGategories")
    public ResponseVO getGategories(@RequestBody QueryCategoryReq request) throws BaseException{
        return proStandService.getCategory(request);
    }


    @ApiOperation(value="获取检测方法详情", notes="获取检测方法详情")
    @ApiImplicitParam(name = "request", value = "请求体", required = true,dataType = "QueryDeteMthReq", paramType = "body")
    @ApiAction("获取检测方法详情")
    @PostMapping("/getDeteMth")
    public ResponseVO getDeteMth(@RequestBody QueryDeteMthReq request) throws BaseException{
        return proStandService.getDetailDeteMth(request);
    }

}
