package com.gxjtkyy.standardcloud.api.controller.doc;


import com.gxjtkyy.standardcloud.common.annotation.ApiAction;
import com.gxjtkyy.standardcloud.common.domain.vo.DocRequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.api.service.DeterStandService;
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
 * 判定标准
 * @Package com.gxjtkyy.controller
 * @Author lizhenhua
 * @Date 2018/6/25 19:03
 */
@Slf4j
@RequestMapping("/doc/deter")
@RestController
public class DeterStandController {


    @Autowired
    private DeterStandService deterStandService;

    @ApiOperation(value="查询判定标准详情", notes="查询判定标准详情")
    @ApiImplicitParam(name = "request", value = "请求体", required = true, dataType = "RequestVO", paramType = "body")
    @PostMapping("/getDetailDoc")
    @ApiAction("查询判定标准详情")
    public ResponseVO getDetailDoc(@RequestBody DocRequestVO request) throws BaseException {
        return deterStandService.getTableDoc(request.getDocId());
    }
}
