package com.gxjtkyy.standardcloud.common.parser;


import com.gxjtkyy.standardcloud.common.constant.DocConstant;
import com.gxjtkyy.standardcloud.common.constant.TemplateConstant;
import com.gxjtkyy.standardcloud.common.domain.dto.TemplateDTO;
import com.gxjtkyy.standardcloud.common.domain.info.AttachInfo;
import com.gxjtkyy.standardcloud.common.domain.info.SheetInfo;
import com.gxjtkyy.standardcloud.common.exception.TemplateException;
import com.gxjtkyy.standardcloud.common.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.gxjtkyy.standardcloud.common.constant.ResultCode.*;


/**
 * excel模板解析抽象类
 *
 * @Package com.gxjtkyy.parser
 * @Author lizhenhua
 * @Date 2018/5/24 15:26
 */
@Slf4j
public abstract class ExcelParser<T> {

    /**
     * 文档模板
     */
    protected TemplateDTO template;

    /**
     * 文件路径
     */
    protected String excelPath;

    public ExcelParser(TemplateDTO template, String excelPath) {
        this.template = template;
        this.excelPath = excelPath;
    }

    /**
     * 获取文档类型
     *
     * @return
     */
    public int getDocType() {
        return this.template.getDocType();
    }


    /**
     * 解析Excel文档
     *
     * @return
     * @throws TemplateException
     */
    public abstract T doParse(String name) throws TemplateException;


    /**
     * 预解析
     * 校验导入的文档是否指定模板，表头中文转换成对应的英文，并根据starRow删除无效行
     *
     * @return
     * @throws TemplateException
     */
    protected Workbook preParse() throws TemplateException {
        Workbook wb = POIUtil.getWBFormExcel(new File(excelPath));
        if (null != wb) {
            DictCacheUtil dictCache = DictCacheUtil.getInstance();
            List<SheetInfo> sheetInfos = template.getCatalog();

            if (sheetInfos.size() != wb.getNumberOfSheets()) {
                log.error(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), "Excel Sheet数目不匹配", wb.getNumberOfSheets() + " ≠ " + sheetInfos.size());
                throw new TemplateException(RESULT_CODE_1002, String.format(RESULT_DESC_1002, "表格数不一致"));
            }

            for (int i = 0, size = sheetInfos.size(); i < size; i++) {
                SheetInfo sheetInfo = sheetInfos.get(i);
                Sheet sheet = wb.getSheetAt(i);
//                if (sheetInfo.getDataModel().equals(TemplateConstant.DATA_MODEL_ATTACH)) {
//                    continue;
//                }
                //sheet name 不匹配
                String sheetCode = dictCache.getDictCode(sheet.getSheetName(), i, -1);
                if (!sheetInfo.getSheetCode().equals(sheetCode)) {
                    log.error(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), "SheetCode 不匹配", sheetCode + " ≠ " + sheetInfo.getSheetCode());
                    throw new TemplateException(RESULT_CODE_1002, String.format(RESULT_DESC_1002, sheet.getSheetName()+"("+sheetCode + " ≠ " + sheetInfo.getSheetCode()+")"));
                }


                List<String> colInfos = sheetInfo.getColumns();
                for (int j = 0, length = colInfos.size(); j < length; j++) {
                    Cell cell = sheet.getRow(0).getCell(j);
                    String cellValue = POIUtil.getCellValue(cell);
                    if (StringUtils.isEmpty(cellValue)) {
                        log.warn(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), "Excel表头预解析", "表头: " + colInfos.get(j) + "不存在");
                        break;
                    }
                    String colCode = dictCache.getDictCode(cellValue, i, j);
                    if (!colInfos.get(j).equals(colCode)) {
                        log.error(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), "Excel表头预解析", sheet.getSheetName()+": 表头不匹配 --> "+colCode + " ≠ " + colInfos.get(j));
                        throw new TemplateException(RESULT_CODE_1002, String.format(RESULT_DESC_1002, sheet.getSheetName()+"("+colCode + " ≠ " + colInfos.get(j))+")");
                    }
                    cell.setCellValue(colInfos.get(j));
                }

//                //匹配列
//                if (!sheetInfo.isSingleColumn()) {
//
//                } else {
//                    //单列表格属性匹配
//                    Cell cell = sheet.getRow(0).getCell(0);
//                    String colName = POIUtil.getCellValue(cell);
//                    if (!sheetInfo.getSheetCode().equals(dictCache.getDictCode(colName, i, 0))) {
//                        //log.error(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), sheet.getSheetName()+": SheetColumn 不匹配", colCode + " ≠ " + colInfos.get(j));
//                        log.error("导入文档与对应模板column不匹配(单列),sheet-->{},current col-->{}, template col-->{}", sheet.getSheetName(), colName, sheetInfo.getSheetCode());
//                        throw new TemplateException(RESULT_CODE_1002, String.format(RESULT_DESC_1002, sheet.getSheetName()));
//                    }
//                    cell.setCellValue(sheetInfo.getSheetCode());
//                }
                //更改表名,注意，表名不能超过32个字符
                wb.setSheetName(i, sheetInfo.getSheetCode() + "$" + sheetInfo.getDataDirection() + "$" + sheetInfo.getStartRow());
            }
        }
        return wb;
    }


    /**
     * 解析Excel为领域对象
     * 使用Map存储
     *
     * @param wb 文档对象
     * @return
     */
    protected Map _doParse(Workbook wb) throws TemplateException {
        if (null == wb) {
            throw new TemplateException(RESULT_CODE_1015, RESULT_DESC_1015);
        }
        Iterator<Sheet> sheets = wb.iterator();
        Map<String, Object> docMap = new LinkedHashMap<>();
        while (sheets.hasNext()) {
            Sheet sheet = sheets.next();
            Row headRow = sheet.getRow(0);
            String sheetName = sheet.getSheetName();
            //数据读取方向
            String[] args = sheetName.split("\\$");
            String sheetCode = args[0];
            String direction = args[1];
            int startRow = Integer.valueOf(args[2]);

            //垂直读取方向
            if (direction.equals(TemplateConstant.DATA_DIRECTION_VERTICAL)) {
                log.info(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(),"解析Excel文档...", "垂直遍历表格("+sheetCode+")");
                int totalColNum = sheet.getRow(0).getPhysicalNumberOfCells();
                int totalRowNum = sheet.getPhysicalNumberOfRows();
                Map<String, Object> sheetMap = new LinkedHashMap<>();
                for (int i = 0; i < totalColNum; i++) {
                    String key = POIUtil.getCellValue(sheet.getRow(0).getCell(i));
                    // 表头字段为空，则跳出循环
                    if (StringUtils.isEmpty(key)) {
                        break;
                    }
                    List<Object> rowList = new ArrayList<>();
                    for (int j = startRow; j < totalRowNum; j++) {
                        if (sheet.getRow(j) == null || sheet.getRow(j).getCell(i) == null || StringUtils.isEmpty(POIUtil.getCellValue(sheet.getRow(j).getCell(i)))) {
                            log.debug(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), "垂直遍历表格("+sheetCode+")", "row: "+j +",col: "+j +" 空白单元格，终止行读取");
                            break;
                        }
                        String value = POIUtil.getCellValue(sheet.getRow(j).getCell(i));
                        rowList.add(convertValue(value));
                    }
                    sheetMap.put(key, rowList);
                }
                docMap.put(sheetCode, sheetMap);
            } else if (direction.equals(TemplateConstant.DATA_DIRECTION_HORIZONTAL)) {
                if (sheet.getMergedRegions().size() > 0) {
                    log.info(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(),"解析Excel文档...", "遍历混合表格("+sheetCode+")");
                    //用map保存合并的单元对象，提高效率，仅对同列行合并有效
                    Map<Integer, CellRangeAddress> categoryMap = new HashMap<>();
                    for (CellRangeAddress rangeAddress : sheet.getMergedRegions()) {
                        if (categoryMap.get(rangeAddress.getFirstColumn()) != null) {
                            log.error(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), "遍历混合表格("+sheetCode+")", "不支持同列多合并单元格读取");
                            throw new TemplateException(RESULT_CODE_1005, String.format(RESULT_DESC_1005, sheetCode));
                        }
                        categoryMap.put(rangeAddress.getFirstColumn(), rangeAddress);
                    }
                    Map<String, Object> pRowMap = new LinkedHashMap<>();
                    List<Map<String, Object>> list = new ArrayList<>();
                    for (int i = startRow, size = sheet.getPhysicalNumberOfRows(); i < size; i++) {
                        boolean breakFlag = false;
                        Map<String, Object> rowMap = new LinkedHashMap<>();
                        for (int j = 0, sizej = sheet.getRow(0).getPhysicalNumberOfCells(); j < sizej; j++) {
                            String key = POIUtil.getCellValue(sheet.getRow(0).getCell(j));
                            String value = POIUtil.getCellValue(sheet.getRow(i).getCell(j));
                            if (StringUtils.isEmpty(key)) {
                                break;
                            }
                            CellRangeAddress rangeAddress = categoryMap.get(j);
                            if (null != rangeAddress) {
                                //cell仍在合并单元格中，则跳过
                                if (pRowMap.get(key) != null && i <= rangeAddress.getLastRow()) {
                                    continue;
                                }
                                if (StringUtils.isEmpty(value)) {
                                    breakFlag = true;
                                    break;
                                }
                                pRowMap.put(key, value);//合并单元格值
                            } else {
                                rowMap.put(key, value);
                            }
                        }
                        if (breakFlag) {
                            break;
                        }
                        list.add(rowMap);
                    }
                    pRowMap.put("children", list);
                    docMap.put(sheetCode, pRowMap);
                }else{
                    log.info(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(),"解析Excel文档...", "水平遍历表格("+sheetCode+")");
                    //水平读取方向
                    List<Map<String, Object>> listMap = new ArrayList<>();
                    for (int i = startRow, size = sheet.getPhysicalNumberOfRows(); i < size; i++) {
                        Map<String, Object> rowMap = new LinkedHashMap<>();
                        if (StringUtils.isEmpty(POIUtil.getCellValue(sheet.getRow(i).getCell(0)))) {
                            break;
                        }
                        rowMap.put(TemplateConstant.TEMPLATE_TABLE_KEY_ROWID, i);
                        for (int j = 0, sizej = sheet.getRow(i).getPhysicalNumberOfCells(); j < sizej; j++) {
                            String key = POIUtil.getCellValue(headRow.getCell(j));
                            String value = POIUtil.getCellValue(sheet.getRow(i).getCell(j));
                            //处理星号*分隔字典
                            if (value.contains("*")) {
                                rowMap.put(key, Arrays.asList(value.split("\\*")));
                                continue;
                            }
                            rowMap.put(key, convertValue(value));
                        }
                        listMap.add(rowMap);
                    }
                    docMap.put(sheetCode, listMap);
                }
            } else {
                log.error(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), "解析Excel文档...", "模板未定义数据读取方向("+sheetCode+")");
                throw new TemplateException(RESULT_CODE_1004, String.format(RESULT_DESC_1004, sheetCode));
            }
        }
        return docMap;
    }


    /**
     * 单元格值转换
     * 若值指向附录文件，则将附录转换成相应的数据结构
     *
     * @param value
     * @return
     * @throws TemplateException
     */
    private Object convertValue(String value) throws TemplateException {
        if (!StringUtils.isEmpty(value) && value.startsWith(TemplateConstant.CELL_ATTACH_PREFIX)) {
            final String atFileStr = value.substring(TemplateConstant.CELL_ATTACH_PREFIX.length());
            String basePath = new File(excelPath).getParent();
            File atFile = new File(basePath + File.separator + atFileStr);
            if (!atFile.exists() || !atFile.isFile()) {
                log.error(DocConstant.LOG_PRINT_FORMAT, BusiUtil.getLogIndex(), "解析Excel文档中附录...", "文件("+value+")不存在");
                throw new TemplateException(RESULT_CODE_1016, String.format(RESULT_DESC_1016, value));
            }
            String fileId = UUID.randomUUID().toString().replace("-", "");
            String originName = atFile.getName();
            String suffix = originName.substring(originName.lastIndexOf("."));
            String dateStrPath = DateUtils.getStringDateClear();

            AttachInfo info = new AttachInfo();
            info.setAttachName(originName);
            info.setId(fileId);
            info.setUrl(DocConstant.STATIC_RESOURCE_PATH + "/" + dateStrPath + "/" + fileId + "" + suffix);
            Environment env = ApplicationContextUtil.getBean(Environment.class);
            final String upload = env.getProperty("upload.path");
            final String logIndex = BusiUtil.getLogIndex();

            ThreadPoolManager.getsInstance().execute(() -> {
                try {
                    String newFilePath = upload + File.separator + "attach" + File.separator + dateStrPath + File.separator + fileId + suffix;
                    log.info("{}@正在拷贝附录...  {}--> {}",logIndex, atFileStr, newFilePath);
                    FileUtils.copyFile(atFile, new File(newFilePath));
                } catch (IOException e) {
                    log.error("{}@拷贝文件失败 --> {}",logIndex, originName, e);
                }
            });
            return info;
        }
        return value;
    }
}
