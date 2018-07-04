package com.gxjtkyy.standardcloud.api.utils;

import com.gxjtkyy.standardcloud.common.utils.POIUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * excel解析测试类
 * @Package com.gxjtkyy.utils
 * @Author lizhenhua
 * @Date 2018/5/22 16:54
 */
public class POITest {

    public static void main(String[] args) {
        String path="F:\\doctemp\\2018版抽样细则~食糖~红糖.xlsx";

        try {
            File file = new File(path);
            if(file.isFile() && file.exists()){
                String[] split = file.getName().split("\\.");
                Workbook wb;
                if("xls".equals(split[1])){
                    FileInputStream fis = new FileInputStream(file);
                    wb = new HSSFWorkbook(fis);
                }else if("xlsx".equals(split[1])){
                    wb = new XSSFWorkbook(file);
                }else{
                    return;
                }
                //解析
                Iterator<Sheet> sheets = wb.sheetIterator();
                while (sheets.hasNext()){
                    Sheet sheet = sheets.next();
                    if(sheet.getSheetName().equals("抽样样品检验项目")){
                        int rowSize = sheet.getLastRowNum();
                        for(int i=1; i < rowSize; i++){
                            Map<String, Object> mthData = new HashMap<>();
                            for(int j =0, sizej = sheet.getRow(i).getLastCellNum(); j < sizej ; j++){
                                String cellText = POIUtil.getCellValue(sheet.getRow(i).getCell(j));
                                System.out.println(cellText);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
