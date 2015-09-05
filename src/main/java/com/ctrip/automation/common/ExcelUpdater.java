package com.ctrip.automation.common;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

import java.io.*;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;

public class ExcelUpdater {

	public static void updateExcel(String fullFilePath,String sheetName,int col,int row,String value)throws Exception{
		File file=new File(fullFilePath);
		FileInputStream fis=new FileInputStream(fullFilePath);
        HSSFWorkbook workbook=new HSSFWorkbook(fis);
//        workbook.
        HSSFSheet sheet=workbook.getSheet(sheetName);

        HSSFRow r=sheet.getRow(row);
        HSSFCell cell=r.getCell(col);
        int type=cell.getCellType();
        //String str1=cell.getStringCellValue();
        ExcelWriter excelWriter=new ExcelWriter();
        HSSFCellStyle style=excelWriter.getStyle("title",value,workbook);
        //这里假设对应单元格原来的类型也是String类型
        cell.setCellValue(value);
        

        fis.close();//关闭文件输入流

        FileOutputStream fos=new FileOutputStream(fullFilePath);
        workbook.write(fos);
        fos.close();//关闭文件输出流
    }


    private String getCellValue(HSSFCell cell) {
        String cellValue = "";
        DecimalFormat df = new DecimalFormat("#");
        switch (cell.getCellType()) {
            case  Cell.CELL_TYPE_STRING :
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = df.format(cell.getNumericCellValue()).toString();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case Cell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "";
        }
        return cellValue;
    }
    
}


