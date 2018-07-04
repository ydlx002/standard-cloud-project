package com.gxjtkyy.standardcloud.common.utils;

import com.alibaba.fastjson.JSON;
import com.gxjtkyy.standardcloud.common.constant.DocConstant;
import com.gxjtkyy.standardcloud.common.domain.dto.ApiLogDTO;
import com.gxjtkyy.standardcloud.common.domain.vo.RequestVO;
import com.gxjtkyy.standardcloud.common.domain.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

/**
 * 日志工具类
 * @Package com.gxjtkyy.standardcloud.common.utils
 * @Author lizhenhua
 * @Date 2018/7/3 15:29
 */
@Slf4j
public class LogUtil {


    /**
     * 保存日志
     * @param logIndex 日志索引
     * @param apiDesc 接口描述
     * @param requestURI 请求路径
     * @param ip IP地址
     * @param operator 操作员
     * @param operatorId 操作员ID
     * @param request 请求体
     * @param response 响应体
     * @param saveLog  是否保存到数据库中
     */
    public static void saveLog(String logIndex, String apiDesc, String requestURI, String ip,
                               String operator, String operatorId, RequestVO request, ResponseVO response,
                               boolean saveLog){
        ApiLogDTO apiLog = new ApiLogDTO();
        apiLog.setUpdateTime(new Date());
        apiLog.setCreateTime(new Date());
        apiLog.setLogIndex(logIndex);
        apiLog.setRequestURI(requestURI);
        apiLog.setIp(ip);
        apiLog.setOperator(operator);
        apiLog.setOperatorId(operatorId);
        apiLog.setRequest(MapUtil.convertBeanToMap(request));
        apiLog.setResponse(MapUtil.convertBeanToMap(response));

        log.info(DocConstant.LOG_PRINT_FORMAT, logIndex, apiDesc, JSON.toJSONString(apiLog));

        if(saveLog){
            ThreadPoolManager.getsInstance().execute(()->{
                MongoTemplate mongoTemplate = ApplicationContextUtil.getBean(MongoTemplate.class);
                mongoTemplate.save(log, DocConstant.COLLECTION_API_LOG);
            });
        }
    }
}
