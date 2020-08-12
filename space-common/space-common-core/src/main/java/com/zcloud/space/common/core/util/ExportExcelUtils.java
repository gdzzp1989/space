package com.zcloud.space.common.core.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @Description 导出excel
 * @Author
 * @Date
 */
public class ExportExcelUtils {

    /**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String [][]title,String [][]values, HSSFWorkbook wb, String[] backTitle){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = null;

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(HorizontalAlignment.CENTER);

        //声明列对象
        HSSFCell cell = null;
        int[][] rows = new int[][]{{13},{5,4,4},{1,4,4,4}};
        //创建标题
        for(int i=0;i<title.length;i++){
            row = sheet.createRow(i);
            if(i == 2){
                sheet.addMergedRegion(new CellRangeAddress(i,i+1,0,0));
            }
            for(int j=0;j<title[i].length;j++){
                if(i == 1 && j > 0){
                    cell = row.createCell(j*4+1);
                    cell.setCellValue(title[i][j]);
                    cell.setCellStyle(style);
                }else if(i == 2 && j > 0){
                    cell = row.createCell(j*4-3);
                    cell.setCellValue(title[i][j]);
                    cell.setCellStyle(style);
                    if(j==title[i].length-1){
                        cell = row.createCell(j*4-2);
                        cell.setCellValue(title[i][j]);
                        cell = row.createCell(j*4-1);
                        cell.setCellValue(title[i][j]);
                        cell = row.createCell(j*4);
                        cell.setCellValue(title[i][j]);
                    }
                }else{
                    cell = row.createCell(j);
                    cell.setCellValue(title[i][j]);
                    cell.setCellStyle(style);
                }
            }
            if(i == title.length-1 && backTitle != null){
                row = sheet.createRow(i+1);
                for(int x = 0; x < title[i].length-1; x ++){
                    for(int j = 0; j<backTitle.length;j++){
                        cell = row.createCell((j+1)+(x*backTitle.length));
                        cell.setCellValue(backTitle[j]);
                        cell.setCellStyle(style);
                    }
                }
            }
        }
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,4));
        sheet.addMergedRegion(new CellRangeAddress(1,1,5,8));
        sheet.addMergedRegion(new CellRangeAddress(1,1,9,12));
        sheet.addMergedRegion(new CellRangeAddress(2,2,1,4));
        sheet.addMergedRegion(new CellRangeAddress(2,2,5,8));
        sheet.addMergedRegion(new CellRangeAddress(2,2,9,12));
        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(title.length+i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

    /**
     * 发送响应流方法
     * @param response
     * @param fileName
     */
    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
