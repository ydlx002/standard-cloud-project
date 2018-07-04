package com.gxjtkyy.standardcloud.common.utils;


/**
 * TheadLocal
 * @Package com.gxjtkyy.quality.utils
 * @Author lizhenhua
 * @Date 2018/6/9 10:08
 */
public class BusiUtil {

    private static final ThreadLocal<String> logIndexLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> busiDescLocal = new ThreadLocal<>();



    public static String getLogIndex(){
        if(null != logIndexLocal){
            return logIndexLocal.get();
        }
        return null;
    }

    public static String getBusiDesc(){
        if(null != busiDescLocal){
            return busiDescLocal.get();
        }
        return null;
    }


    public static String setLogIndex(String logIndex){
        logIndexLocal.set(logIndex);
        return logIndex;
    }

    public static String setBusiDesc(String busiDesc){
        busiDescLocal.set(busiDesc);
        return busiDesc;
    }


    public static void removeLogIndex(){
        if(null != logIndexLocal.get()){
            logIndexLocal.remove();
        }
    }

    public static void removeBusiDesc(){
        if(null != busiDescLocal.get()){
            busiDescLocal.remove();
        }
    }

}
