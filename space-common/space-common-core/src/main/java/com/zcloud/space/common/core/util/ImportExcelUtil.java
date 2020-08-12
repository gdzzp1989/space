package com.zcloud.space.common.core.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zcloud.space.common.core.annotation.ExcelImportAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Description Excel表格导入工具类
 * @Author
 * @Date
 */
@Slf4j
public class ImportExcelUtil<T> {

    private final Class<T> importClass;

    private List<T> dataList = new ArrayList<>();

    public ImportExcelUtil(Class<T> clazz) {
        this.importClass = clazz;
        if (clazz == null) {
            throw new NullPointerException("clazz 不能为空");
        }
    }

    /**
     * @description 获取Excel表格中的数据
     *
     * @param
     * @param workbook
     * @return
     */
    public List<T> getExcelInfo(int tableHeadRows, XSSFWorkbook workbook) throws Exception {
        // 获取Excel文档中所有sheet对象
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();

        if (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            return this.operateRowsData(tableHeadRows, sheet);
        }
        return Collections.emptyList();
    }

    /**
     * @description 操作Excel表格中的行数据
     *
     * @param tableHeadRows 表头占的行数
     * @param sheet
     * @return
     */
    private List<T> operateRowsData(int tableHeadRows, Sheet sheet) throws Exception {
        // 对Sheet中的每一行进行迭代
        for (Row row : sheet) {
            // 如果当前行的行号（从0开始）未达到表头占的行数则重新循环
            if (row.getRowNum() < tableHeadRows) {
                continue;
            }

            dataList.add(this.operateCellsData(tableHeadRows, row));
        }
        return dataList;
    }

    /**
     * @description 操作Excel表格中的单元格数据
     *
     * @param tableHeadRows
     * @param row
     * @return
     */
    private T operateCellsData(int tableHeadRows, Row row) throws Exception {
        try {
            T instance = importClass.newInstance();

            Field[] fields = this.importClass.getDeclaredFields();

            for (Field field : fields) {
                // Excel表格导入注解
                ExcelImportAnnotation annotation = field.getAnnotation(ExcelImportAnnotation.class);
                // 当前操作的单元格对象
                Cell cell = row.getCell(annotation.order());
                // 当前操作的单元格位置
                String currentLocation = (char) (annotation.order() + 65) + String.valueOf(row.getRowNum() + tableHeadRows);

                if (ObjectUtil.isNull(cell)) {
                    log.warn("单元格获取失败，位置：{}", currentLocation);
                    throw new Exception("单元格获取失败！");
                }

                this.addValueToField(instance, annotation, field, cell, currentLocation);
            }
            return instance;
        } catch (InstantiationException e) {
            log.warn("通过Class对象{}反射创建实例失败！");
            throw new InstantiationException("通过Class对象" + this.importClass + "反射创建实例失败！");
        }
    }

    /**
     * @description 将从cell中获取的数据存入相应的字段
     *
     * @param instance
     * @param annotation Excel表格导入注解
     * @param field 当前操作的字段
     * @param cell 当前操作的单元格
     * @param currentLocation 当前操作的单元格位置
     * @return
     */
    private void addValueToField(Object instance, ExcelImportAnnotation annotation, Field field, Cell cell, String currentLocation) throws Exception {
        try {
            /*单元格STRING类型*/
            if (ObjectUtil.equal(cell.getCellTypeEnum(), CellType.STRING)) {
                // 日期格式
                Date date = isFormatAsDate(cell.getStringCellValue());
                if (ObjectUtil.isNotNull(date)) {
                    cell.setCellValue(date);
                    this.importClass.getMethod(annotation.method(), field.getType())
                            .invoke(instance, cell.getDateCellValue());
                    return;
                }
                // 字符串格式
                this.importClass.getMethod(annotation.method(), field.getType())
                        .invoke(instance, cell.getStringCellValue());

                /*单元格NUMERIC类型*/
            } else if (ObjectUtil.equal(cell.getCellTypeEnum(), CellType.NUMERIC)) {
                // 日期格式类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    this.importClass.getMethod(annotation.method(), field.getType())
                            .invoke(instance, cell.getDateCellValue());
                    return;
                }
                // 数字格式
                // Byte类型
                if (ObjectUtil.equal(field.getType(), Byte.class)) {
                    this.importClass.getMethod(annotation.method(), field.getType())
                            .invoke(instance, (byte) cell.getNumericCellValue());
                    return;
                }

                // Integer类型
                if (ObjectUtil.equal(field.getType(), Integer.class)) {
                    this.importClass.getMethod(annotation.method(), field.getType())
                            .invoke(instance, (int) cell.getNumericCellValue());
                    return;
                }

                // String类型
                if (ObjectUtil.equal(field.getType(), String.class)) {
                    this.importClass.getMethod(annotation.method(), field.getType())
                            .invoke(instance, String.valueOf((int) cell.getNumericCellValue()));
                    return;
                }

                this.importClass.getMethod(annotation.method(), field.getType())
                        .invoke(instance, cell.getNumericCellValue());

                /*单元格BOOLEAN类型*/
            } else if (ObjectUtil.equal(cell.getCellTypeEnum(), CellType.BOOLEAN)) {
                this.importClass.getMethod(annotation.method(), field.getType())
                        .invoke(instance, cell.getBooleanCellValue());

                /*单元格BLANK类型*/
            } else if (ObjectUtil.equal(cell.getCellTypeEnum(), CellType.BLANK)) {
                if (ObjectUtil.equal(field.getType(), Date.class)) {
                    this.importClass.getMethod(annotation.method(), field.getType())
                            .invoke(instance, (Date) null);
                } else if (ObjectUtil.equal(field.getType(), Double.class)) {
                    this.importClass.getMethod(annotation.method(), field.getType())
                            .invoke(instance, (Double) null);
                } else if (ObjectUtil.equal(field.getType(), Byte.class)) {
                    this.importClass.getMethod(annotation.method(), field.getType())
                            .invoke(instance, (Byte) null);
                } else if (ObjectUtil.equal(field.getType(), Integer.class)) {
                    this.importClass.getMethod(annotation.method(), field.getType())
                            .invoke(instance, (Integer) null);
                } else {
                    this.importClass.getMethod(annotation.method(), field.getType())
                            .invoke(instance, "");
                }

                /*单元格FORMULA类型*/
            } else if (ObjectUtil.equal(cell.getCellTypeEnum(), CellType.FORMULA)) {
                this.importClass.getMethod(annotation.method(), field.getType())
                        .invoke(instance, cell.getCellFormula());

            } else {
                log.warn("单元格格式有误，位置：{}", currentLocation);
                throw new Exception("单元格格式有误！");
            }
        } catch (NoSuchMethodException e) {
            log.warn("当前操作的单元格对应字段{}【{}】没有{}方法。", field.getName(), annotation.columnTitle(), annotation.method());
            throw new NoSuchMethodException("当前操作的单元格对应字段" + field.getName() + "【" + annotation.columnTitle() + "】没有" + annotation.method() + "方法。");
        } catch (IllegalArgumentException e) {
            log.warn("参数类型不匹配，位置：{}", currentLocation);
            throw e;
        }
    }

    /**
     * @description 判断单元格中String类型数据是否可以转换成Date类型数据
     *
     * @param cellData 单元格数据
     * @return
     */
    private Date isFormatAsDate(String cellData) {
        try {
            return DateUtil.parse(cellData, DatePattern.NORM_DATETIME_FORMAT);
        } catch (Exception e) {
            log.warn("当前操作的单元格STRING类型的数据【{}】不能转换为日期格式！", cellData);
            return null;
        }
    }

}
