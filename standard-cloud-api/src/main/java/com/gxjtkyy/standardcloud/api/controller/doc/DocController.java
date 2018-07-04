package com.gxjtkyy.standardcloud.api.controller.doc;

import com.gxjtkyy.standardcloud.api.domain.vo.request.KeySearchReq;
import com.gxjtkyy.standardcloud.api.domain.vo.request.QueryDictReq;
import com.gxjtkyy.standardcloud.common.annotation.ApiAction;
import com.gxjtkyy.standardcloud.common.annotation.LogAction;
import com.gxjtkyy.standardcloud.common.domain.vo.DocRequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.api.service.DocService;
import com.gxjtkyy.standardcloud.api.service.StandDocService;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 文档管理控制器
 *
 * @Package com.gxjtkyy.controller
 * @Author lizhenhua
 * @Date 2018/5/29 8:46
 */
@Slf4j
@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    private DocService docService;

    @Autowired
    private StandDocService standDocService;


    @ApiOperation(value = "查询标准文档", notes = "根据关键字查询标准文档")
    @ApiImplicitParam(name = "request", value = "请求体", required = true, dataType = "KeySearchReq", paramType = "body")
    @PostMapping("/search")
    @ApiAction("查询标准文档")
    public  ResponseVO search(@RequestBody KeySearchReq request) throws BaseException{
        return docService.searchDocs(request);
    }


    /**
     * 获取所有的字典数据
     *
     * @return
     */
    @ApiOperation(value = "获取字典数据", notes = "获取字典数据")
    @ApiImplicitParam(name = "request", value = "请求体", required = true, dataType = "QueryDictReq", paramType = "body")
    @PostMapping("/dict/getDicts")
    @ApiAction("获取字典数据")
    public ResponseVO getDicts(@RequestBody QueryDictReq request) {
        return docService.getDicts(request);
    }


    @ApiOperation(value = "获取导航栏", notes = "获取导航栏")
    @ApiImplicitParam(name = "request", value = "请求体", required = true, dataType = "RequestVO", paramType = "body")
    @PostMapping("/getNavigation")
    @ApiAction("获取导航栏")
    public ResponseVO getNavigation(@RequestBody DocRequestVO request) throws BaseException {
        return standDocService.getNavigation(request.getDocId());
    }

}
