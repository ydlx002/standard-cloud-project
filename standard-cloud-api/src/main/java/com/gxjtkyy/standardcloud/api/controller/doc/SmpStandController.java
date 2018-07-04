package com.gxjtkyy.standardcloud.api.controller.doc;


import com.gxjtkyy.standardcloud.common.annotation.ApiAction;
import com.gxjtkyy.standardcloud.common.domain.vo.DocRequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import com.gxjtkyy.standardcloud.api.service.SmpStandService;
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
 * 抽样细则控制器
 * @Package com.gxjtkyy.controller
 * @Author lizhenhua
 * @Date 2018/6/25 18:22
 */
@RestController
@Slf4j
@RequestMapping("/doc/smp")
public class SmpStandController {

    @Autowired
    private SmpStandService smpStandService;

    @ApiOperation(value="获取抽样细则详情", notes="获取抽样细则详情")
    @ApiImplicitParam(name = "request", value = "请求体", required = true, dataType = "RequestVO", paramType = "body")
    @ApiAction("获取抽样细则详情")
    @PostMapping("/getDetailDoc")
    public ResponseVO getDetailDoc(@RequestBody DocRequestVO request) throws BaseException {
        return smpStandService.getSmpDetailDoc(request.getDocId());
    }

}
