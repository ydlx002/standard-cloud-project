package com.gxjtkyy.standardcloud.admin.controller;

import com.gxjtkyy.standardcloud.admin.domain.vo.request.AddDictReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryDictPageReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.QueryDocPageReq;
import com.gxjtkyy.standardcloud.admin.domain.vo.request.UpdateDictReq;
import com.gxjtkyy.standardcloud.admin.service.DictService;
import com.gxjtkyy.standardcloud.admin.service.DocService;
import com.gxjtkyy.standardcloud.common.annotation.ApiAction;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.exception.DocException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.gxjtkyy.standardcloud.admin.controller
 * @Author lizhenhua
 * @Date 2018/6/28 15:15
 */
@Slf4j
@RestController
@RequestMapping("/admin/dict")
public class DictController {



    @Autowired
    private DictService dictService;


    @ApiOperation(value="分页查询字典列表", notes="分页查询字典列表")
    @ApiImplicitParam(name = "request", value = "请求体", required = true, dataType = "QueryDictPageReq", paramType = "body")
    @ApiAction("分页查询字典列表")
    @PostMapping("/queryTable")
    public ResponseVO queryTable(@RequestBody QueryDictPageReq request) throws BaseException {
        return dictService.getListByPage(request);
    }

    @ApiOperation(value="更新字典值", notes="更新字典值")
    @ApiImplicitParam(name = "request", value = "请求体", required = true, dataType = "UpdateDictReq", paramType = "body")
    @ApiAction("更新字典值")
    @PostMapping("/update")
    public ResponseVO update(@RequestBody UpdateDictReq request) throws BaseException {
        return dictService.updateDict(request);
    }

    @ApiOperation(value="新增字典值", notes="新增字典值")
    @ApiImplicitParam(name = "request", value = "请求体", required = true, dataType = "AddDictReq", paramType = "body")
    @ApiAction("新增字典值")
    @PostMapping("/add")
    public ResponseVO add(@RequestBody AddDictReq request) throws BaseException {
        return dictService.addDict(request);
    }
}
