package com.gxjtkyy.standardcloud.common.utils;


import com.alibaba.druid.util.StringUtils;
import com.gxjtkyy.standardcloud.common.exception.BaseException;
import com.gxjtkyy.standardcloud.common.exception.TemplateException;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 文件工具类
 *
 * @Package com.gxjtkyy.utils
 * @Author lizhenhua
 * @Date 2018/5/24 15:44
 */
@Slf4j
public class POIUtil {

    /**
     * 从文本路径从读取Workbook对象
     *
     * @param file 文本路径
     * @return
     */
    public static Workbook getWBFormExcel(File file) {
        try {
            if (file.isFile() && file.exists()) {
                String[] split = file.getName().split("\\.");
                int index = split.length - 1;
                if ("xls".equals(split[index])) {
                    @Cleanup FileInputStream fis = new FileInputStream(file);
                    return new HSSFWorkbook(fis);
                } else if ("xlsx".equals(split[index])) {
                    return new XSSFWorkbook(file);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("发生异常", e);
        }
        return null;
    }

    /**
     * 设置表头字典映射
     * 遍历表头中文，转换成相应的属性值，并以序号为KEY，值为VALUE存放于Map中
     *
     * @param sheet     页对象
     * @param dictMap   模板字典
     * @param headerMap 表头字典
     * @throws BaseException
     */
    public static void setHeaderDict(Sheet sheet, Map<String, String> dictMap, Map<Integer, String> headerMap) throws BaseException {
        headerMap.clear();
        int i = 0;
        Iterator<Cell> cells = sheet.getRow(0).cellIterator();
        while (cells.hasNext()) {
            Cell cell = cells.next();
            String key = cell.getRichStringCellValue().getString();
            if (StringUtils.isEmpty(key)) {
                break;
            }
            String code = dictMap.get(key);
            if (StringUtils.isEmpty(code)) {
                throw new TemplateException("页[" + sheet.getSheetName() + "]表头存在空单元格：列[" + i + "]");
            }
            headerMap.put(i, code);
            i++;
        }
    }


    /**
     * 获取表格单元值
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        if (cell instanceof XSSFCell) {
            return getXssfCellValue((XSSFCell) cell);
        } else if (cell instanceof HSSFCell) {
            return getHssfCellValue((HSSFCell) cell);
        } else {
            return "";
        }
    }

    /**
     * 获取xlsx表格单元值
     *
     * @param xssfCell
     * @return
     */
    private static String getXssfCellValue(XSSFCell xssfCell) {
        String cellvalue = "";
        switch (xssfCell.getCellType()) {
            case XSSFCell.CELL_TYPE_NUMERIC: // 数字
                if (DateUtil.isCellDateFormatted(xssfCell)) {
                    cellvalue = new SimpleDateFormat("yyyy/MM/dd").format(DateUtil.getJavaDate(xssfCell.getNumericCellValue()));
                } else {
                    double value = xssfCell.getNumericCellValue();
                    int intValue = (int) value;
                    cellvalue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
                }
                break;
            case XSSFCell.CELL_TYPE_STRING: // 字符串
                cellvalue = xssfCell.getStringCellValue();
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                cellvalue = String.valueOf(xssfCell.getBooleanCellValue());
                break;
            case XSSFCell.CELL_TYPE_FORMULA: // 公式
                cellvalue = String.valueOf(xssfCell.getCellFormula());
                break;
            case XSSFCell.CELL_TYPE_BLANK: // 空值
                cellvalue = "";
                break;
            case XSSFCell.CELL_TYPE_ERROR: // 故障
                cellvalue = "";
                break;
            default:
                cellvalue = "UNKNOWN TYPE";
                break;
        }
        return cellvalue.trim();
    }


    /**
     * 获取xls表格单元值
     *
     * @param hssfCell
     * @return
     */
    private static String getHssfCellValue(HSSFCell hssfCell) {
        String cellvalue = "";
        switch (hssfCell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                if (DateUtil.isCellDateFormatted(hssfCell)) {
                    cellvalue = new SimpleDateFormat("yyyy/MM/dd").format(DateUtil.getJavaDate(hssfCell.getNumericCellValue()));
                } else {
                    double value = hssfCell.getNumericCellValue();
                    int intValue = (int) value;
                    cellvalue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
                }
                break;
            case HSSFCell.CELL_TYPE_STRING: // 字符串
                cellvalue = hssfCell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                cellvalue = String.valueOf(hssfCell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                cellvalue = String.valueOf(hssfCell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
                cellvalue = "";
                break;
            case HSSFCell.CELL_TYPE_ERROR: // 故障
                cellvalue = "";
                break;
            default:
                cellvalue = "UNKNOWN TYPE";
                break;
        }
        return cellvalue.trim();
    }


    public String importExcel(InputStream inputStream, String fileName) throws Exception {

        String message = "Import success";

        boolean isE2007 = false;
        //判断是否是excel2007格式
        if (fileName.endsWith("xlsx")) {
            isE2007 = true;
        }

        int rowIndex = 0;
        try {
            InputStream input = inputStream;  //建立输入流
            Workbook wb;
            //根据文件格式(2003或者2007)来初始化
            if (isE2007) {
                wb = new XSSFWorkbook(input);
            } else {
                wb = new HSSFWorkbook(input);
            }
            Sheet sheet = wb.getSheetAt(0);    //获得第一个表单
            int rowCount = sheet.getLastRowNum() + 1;

            for (int i = 1; i < rowCount; i++) {
                rowIndex = i;
                Row row;

                for (int j = 0; j < 26; j++) {
                    if (isMergedRegion(sheet, i, j)) {
                        System.out.print(getMergedRegionValue(sheet, i, j) + "\t");
                    } else {
                        row = sheet.getRow(i);
                        System.out.print(row.getCell(j) + "\t");
                    }
                }
                System.out.print("\n");
            }
        } catch (Exception ex) {
            message = "Import failed, please check the dao in " + rowIndex + " rows ";
        }
        return message;
    }


    /**
     * 合并单元格处理,获取合并行
     *
     * @param sheet
     * @return List<CellRangeAddress>
     */
    public List<CellRangeAddress> getCombineCell(Sheet sheet) {
        List<CellRangeAddress> list = new ArrayList<>();
        //获得一个 sheet 中合并单元格的数量
        int sheetmergerCount = sheet.getNumMergedRegions();
        //遍历所有的合并单元格
        for (int i = 0; i < sheetmergerCount; i++) {
            //获得合并单元格保存进list中
            CellRangeAddress ca = sheet.getMergedRegion(i);
            list.add(ca);
        }
        return list;
    }

    private int getRowNum(List<CellRangeAddress> listCombineCell, Cell cell, Sheet sheet) {
        int xr = 0;
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        for (CellRangeAddress ca : listCombineCell) {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if (cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR) {
                if (cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC) {
                    xr = lastR;
                }
            }

        }
        return xr;

    }


    /**
     * 判断单元格是否为合并单元格，是的话则将单元格的值返回
     *
     * @param listCombineCell 存放合并单元格的list
     * @param cell            需要判断的单元格
     * @param sheet           sheet
     * @return
     */
    public String isCombineCell(List<CellRangeAddress> listCombineCell, Cell cell, Sheet sheet)
            throws Exception {
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        String cellValue = null;
        for (CellRangeAddress ca : listCombineCell) {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if (cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR) {
                if (cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC) {
                    Row fRow = sheet.getRow(firstR);
                    Cell fCell = fRow.getCell(firstC);
                    cellValue = getCellValue(fCell);
                    break;
                }
            } else {
                cellValue = "";
            }
        }
        return cellValue;
    }

    /**
     * 获取合并单元格的值
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static String getMergedRegionValue(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell);
                }
            }
        }

        return null;
    }


    /**
     * 判断指定的单元格是否是合并单元格
     *
     * @param sheet
     * @param row    行下标
     * @param column 列下标
     * @return
     */
    public static boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }
}
