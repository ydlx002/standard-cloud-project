package com.gxjtkyy.standardcloud.common.constant;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 结果码
 * @Package com.gxjtkyy.constant
 * @Author lizhenhua
 * @Date 2018/6/7 17:02
 */
public class ResultCode {

    private static final Map<String, String> dataMap = new ConcurrentHashMap<>();

    public static final String RESULT_CODE_0000 = "0000";
    public static final String RESULT_DESC_0000 = "success";

    public static final String RESULT_CODE_9999 = "9999";
    public static final String RESULT_DESC_9999 = "fail";

    /**
     * paramvalidator
     */
    public static final String RESULT_CODE_0001 = "0001";
    public static final String RESULT_DESC_0001 = "不能为空";

    public static final String RESULT_CODE_0002 = "0002";
    public static final String RESULT_DESC_0002 = "类型不支持";

    public static final String RESULT_CODE_0003 = "0003";
    public static final String RESULT_DESC_0003 = "长度必须介于%s 和%s之间";

    public static final String RESULT_CODE_0004 = "0004";
    public static final String RESULT_DESC_0004 = "格式不正确";

    /***
     * 1000 ~ 1999 模板类错误
     */
    public static final String RESULT_CODE_1000 = "1000";
    public static final String RESULT_DESC_1000 = "字典定义不存在[%s]";

    /**模板不存在*/
    public static final String RESULT_CODE_1001 = "1001";
    public static final String RESULT_DESC_1001 = "模板不存在[%s]";

    public static final String RESULT_CODE_1002 = "1002";
    public static final String RESULT_DESC_1002 = "导入文档与对应模板不匹配[%s]";

    public static final String RESULT_CODE_1003 = "1003";
    public static final String RESULT_DESC_1003 = "导入文档与对应模板列数不匹配[%s]";

    public static final String RESULT_CODE_1004 = "1004";
    public static final String RESULT_DESC_1004 = "模板未定义数据读取方向[%s]";

    public static final String RESULT_CODE_1005 = "1005";
    public static final String RESULT_DESC_1005 = "不支持该文档的解析[%s]";

    public static final String RESULT_CODE_1006 = "1006";
    public static final String RESULT_DESC_1006 = "模板配置读取方向错误[%s]";

    public static final String RESULT_CODE_1007 = "1007";
    public static final String RESULT_DESC_1007 = "文档不存在";

    public static final String RESULT_CODE_1008 = "1008";
    public static final String RESULT_DESC_1008 = "无效文档类型[%s]";

    public static final String RESULT_CODE_1009 = "1009";
    public static final String RESULT_DESC_1009 = "文档ID不能为空";

    public static final String RESULT_CODE_1010 = "1010";
    public static final String RESULT_DESC_1010 = "检测方法名不能为空";

    public static final String RESULT_CODE_1011 = "1011";
    public static final String RESULT_DESC_1011 = "检测依据不能为空";

    public static final String RESULT_CODE_1012 = "1012";
    public static final String RESULT_DESC_1012 = "模板ID不能为空";

    public static final String RESULT_CODE_1013 = "1013";
    public static final String RESULT_DESC_1013 = "文件类型不支持";

    public static final String RESULT_CODE_1014 = "1014";
    public static final String RESULT_DESC_1014 = "文档类型ID不能为空";

    public static final String RESULT_CODE_1015 = "1015";
    public static final String RESULT_DESC_1015 = "Excel解析失败";

    public static final String RESULT_CODE_1016 = "1016";
    public static final String RESULT_DESC_1016 = "附录不存在[%s]";

    public static final String RESULT_CODE_1017 = "1017";
    public static final String RESULT_DESC_1017 = "node 不能为空";

    public static final String RESULT_CODE_1018 = "1018";
    public static final String RESULT_DESC_1018 = "currentPage 不能小于1";

    public static final String RESULT_CODE_1019 = "1019";
    public static final String RESULT_DESC_1019 = "pageSize 不能小于1";

    public static final String RESULT_CODE_1020 = "1020";
    public static final String RESULT_DESC_1020 = "dataModel和dataDirection不能同时为空";

    public static final String RESULT_CODE_1021 = "1021";
    public static final String RESULT_DESC_1021 = "字典ID不能为空";

    public static final String RESULT_CODE_1022 = "1022";
    public static final String RESULT_DESC_1022 = "字典值不存在";

    public static final String RESULT_CODE_1023 = "1023";
    public static final String RESULT_DESC_1023 = "字典编码已存在";

    public static final String RESULT_CODE_1024 = "1024";
    public static final String RESULT_DESC_1024 = "字典名称已存在";


    /**
     * 2000 ~ 2999 文档类错误
     */



    static {
        Field[] fields = ResultCode.class.getDeclaredFields();
        try {
            Class clazz = Class.forName("com.gxjtkyy.standardcloud.common.constant.ResultCode");
            for(int i = 0 , len = fields.length; i < len; i++) {
                // 对于每个属性，获取属性名
                String name = fields[i].getName();
                String value = fields[i].get(clazz).toString();
                if(name.startsWith("RESULT_")){
                    dataMap.put(name, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDesc(String resultCode){
        return dataMap.get("RESULT_DESC_"+resultCode);
    }


}
