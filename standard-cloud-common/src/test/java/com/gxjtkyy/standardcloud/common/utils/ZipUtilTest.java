package com.gxjtkyy.standardcloud.common.utils;


import java.io.File;

/**
 * @Package com.gxjtkyy.standardcloud.common.utils
 * @Author lizhenhua
 * @Date 2018/6/29 12:28
 */
public class ZipUtilTest {


    public static void main(String[] args) {
//        try {
//            ZipUtil.unzip("F:\\doctemp\\pro_standard.zip","F:\\docTemp\\test");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        File file = new File("F:\\docTemp\\test");
        for(String t : file.list()){
            System.out.println(t);
        }
    }
}