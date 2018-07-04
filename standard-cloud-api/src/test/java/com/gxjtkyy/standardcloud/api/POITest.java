package com.gxjtkyy.standardcloud.api;

import com.gxjtkyy.standardcloud.common.utils.POIUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @Package com.gxjtkyy
 * @Author lizhenhua
 * @Date 2018/6/12 10:31
 */
public class POITest {

    public static void main(String[] args){

//        try{
//
//            FileInputStream is = new FileInputStream("F:\\doctemp\\pro_standard.xlsx");
//            XSSFWorkbook workbook = new XSSFWorkbook(is);
//            Sheet sheet = workbook.getSheetAt(0);
//            sheet.shiftRows(3,4,-2);
//            FileOutputStream os = new FileOutputStream("F:\\doctemp\\pro_standard_test.xlsx");
//            workbook.write(os);
//            is.close();
//            os.close();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        TestCombile();
    }

    /**
     * Remove a row by its index
     * @param sheet a Excel sheet
     * @param rowIndex a 0 based index of removing row
     */
    public static void removeRow(Sheet sheet, int rowIndex) {
        int lastRowNum=sheet.getLastRowNum();
        if(rowIndex>=0&&rowIndex<lastRowNum)
            sheet.shiftRows(rowIndex+1,lastRowNum,-1);//将行号为rowIndex+1一直到行号为lastRowNum的单元格全部上移一行，以便删除rowIndex行
        if(rowIndex==lastRowNum){
            Row removingRow=sheet.getRow(rowIndex);
            if(removingRow!=null)
                sheet.removeRow(removingRow);
        }
    }



    public static void TestCombile(){

        try {
            FileInputStream is = new FileInputStream("F:\\doctemp\\test_combile.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            if(sheet.getMergedRegions().size() > 0){
                //用map保存合并的单元对象，提高效率，仅对同列行合并有效
                Map<Integer,CellRangeAddress> categoryMap = new HashMap<>();
                for(CellRangeAddress rangeAddress: sheet.getMergedRegions()){
                    categoryMap.put(rangeAddress.getFirstColumn(), rangeAddress);
                }
                Map<String, Object> pRowMap = new LinkedHashMap<>();
                List<Map<String, Object>> list = new ArrayList<>();
                for(int i = 1, size = sheet.getPhysicalNumberOfRows(); i < size; i++){
                    boolean breakFlag = false;
                    Map<String, Object> rowMap = new HashMap<>();
                    for(int j = 0, sizej = sheet.getRow(0).getPhysicalNumberOfCells(); j < sizej; j++){
                        String key = POIUtil.getCellValue(sheet.getRow(0).getCell(j));
                        String value = POIUtil.getCellValue(sheet.getRow(i).getCell(j));
                        if(StringUtils.isEmpty(key)){
                            break;
                        }
                        CellRangeAddress rangeAddress = categoryMap.get(j);
                        if(null != rangeAddress){
                            //cell仍在合并单元格中，则跳过
                            if(pRowMap.get(key) != null && i <= rangeAddress.getLastRow()){
                                continue;
                            }
                            if(StringUtils.isEmpty(value)){
                                breakFlag =true;
                                break;
                            }
                            pRowMap.put(key, value);//合并单元格值
                        }else{
                            rowMap.put(key, value);
                        }
                    }
                    if(breakFlag){
                        break;
                    }
                    list.add(rowMap);
                }
                pRowMap.put("children", list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
