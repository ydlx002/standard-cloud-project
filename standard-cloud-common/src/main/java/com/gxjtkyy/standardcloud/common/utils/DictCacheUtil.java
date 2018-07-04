package com.gxjtkyy.standardcloud.common.utils;


import com.gxjtkyy.standardcloud.common.constant.DocConstant;
import com.gxjtkyy.standardcloud.common.dao.DictComMapper;
import com.gxjtkyy.standardcloud.common.domain.dto.DictDTO;
import com.gxjtkyy.standardcloud.common.exception.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.gxjtkyy.standardcloud.common.constant.ResultCode.RESULT_CODE_1000;
import static com.gxjtkyy.standardcloud.common.constant.ResultCode.RESULT_DESC_1000;


/**
 * 缓存工具类
 * @Package com.gxjtkyy.utils
 * @Author lizhenhua
 * @Date 2018/6/11 15:39
 */
@Slf4j
public class DictCacheUtil {

    //更新缓存时间
    private volatile long updateTime = 0L;

    //正在更新时的阀门
    private volatile boolean updateFlag = true;

    private volatile static DictCacheUtil cacheObject;

    private static Map<String, String> cacheMap = new ConcurrentHashMap<>();

    private DictCacheUtil(){
        this.loadCache();
        updateTime = System.currentTimeMillis();
    }

    /**
     * 获取缓存实例对象
     * @return
     */
    public static DictCacheUtil getInstance(){
        if(null == cacheObject){
            synchronized (DictCacheUtil.class){
                if(null == cacheObject){
                    cacheObject = new DictCacheUtil();
                }
            }
        }
        return cacheObject;
    }

    /**
     * 加载缓存数据
     */
    private void loadCache(){
        this.updateFlag = true;
        DictComMapper dictMapper = ApplicationContextUtil.getBean("dictComMapper");
        List<DictDTO> list =  dictMapper.getList(null);
        for(DictDTO dto: list){
            cacheMap.put(dto.getDictName(), dto.getDictCode());
        }
        this.updateFlag = false;
    }

    /**
     * 返回缓存对象
     *
     * @return
     */
    public Map<String, String> getMapCache() {
        long currentTime = System.currentTimeMillis();
        if (this.updateFlag) {// 前缓存正在更新
            log.info("cache is Instance .....");
            return null;
        }

        if (this.IsTimeOut(currentTime)) {// 如果当前缓存正在更新或者缓存超出时限，需重新加载
            synchronized (this) {
                this.reLoadCache();
                this.updateTime = currentTime;
            }
        }
        return this.cacheMap;
    }

    private boolean IsTimeOut(long currentTime) {
        return ((currentTime - this.updateTime) > 1000000);// 超过时限，超时
    }

    /**
     * 获取缓存项大小
     * @return
     */
    private int getCacheSize() {
        return cacheMap.size();
    }

    /**
     * 获取更新时间
     * @return
     */
    private long getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 获取更新标志
     * @return
     */
    private boolean getUpdateFlag() {
        return this.updateFlag;
    }

    /**
     * 重新装载
     */
    private void reLoadCache() {
        this.cacheMap.clear();
        this.loadCache();
    }

    public String getDictCode(String dictName, int sheetNum, int coNum) throws TemplateException {
        String dictCode = cacheMap.get(dictName);
        //查询不到字典码，则刷新缓存
        if(StringUtils.isEmpty(dictCode)){
            DictComMapper dictMapper = ApplicationContextUtil.getBean("dictComMapper");
            List<DictDTO> dtos = dictMapper.getByName(dictName);
            if(null != dtos && dtos.size() > 0){
                //重新加载缓存
                log.info(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(),"获取字典编码...","字典{"+dictName+"}存在, 重新加载缓存...");
                this.reLoadCache();
                return dtos.get(0).getDictCode();
            }else{
                log.error(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(),"获取字典编码...","字典{"+dictName+"}不存在, at sheet num-->" + sheetNum +" , cols num-->"+coNum);
                throw new TemplateException(RESULT_CODE_1000, String.format(RESULT_DESC_1000, dictName));
            }
        }
        return dictCode;
    }

}
