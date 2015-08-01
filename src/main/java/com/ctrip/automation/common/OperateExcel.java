package com.ctrip.automation.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class OperateExcel {
    

		public void CreateExcelFile(String fullFilePath,String sheetName,List value,String styleType) throws IOException
        {
        	File file=new File(fullFilePath);
        	HSSFWorkbook wb = new HSSFWorkbook();
        	HSSFSheet sheet = wb.createSheet();	
        	//判断文件是否存在
        	if(file.exists())
        	{
        	FileInputStream fs=new FileInputStream(fullFilePath);
        	POIFSFileSystem ps=new POIFSFileSystem(fs);
        	wb = new HSSFWorkbook(ps);
        	sheet =wb.getSheet(sheetName);
        	}
        	else
        	{
        	sheet = wb.createSheet(sheetName);		
        	}
        	          
                // 设置excel每列宽度
                sheet.setColumnWidth(0, 4000);
                sheet.setColumnWidth(1, 2500);
                
                // 创建Excel的sheet的一行
                HSSFRow row=sheet.getRow(0);
                row=sheet.createRow((short)(sheet.getLastRowNum()+1)); 
                row.setHeight((short) 400);// 设定行的高度


                for(int i=0; i<value.size();i++ )
                {
                // 给Excel的单元格设置样式和赋值
                String cellValue=value.get(i).toString();
                HSSFCellStyle style=getStyle(styleType,cellValue,wb);
                HSSFCell cell=row.createCell(i); 
                cell.setCellValue(cellValue);
                cell.setCellStyle(style);
               
                }
            
                FileOutputStream os = new FileOutputStream(fullFilePath);
                os.flush();
                wb.write(os);
                os.close();
        }
        
        public HSSFCellStyle getStyle(String type,String value,HSSFWorkbook wb)    
        {
        	
        	//-----设置表头样式
            // 创建字体样式
            HSSFFont fontTitle = wb.createFont();
            fontTitle.setFontName("微软雅黑");
            fontTitle.setBoldweight((short) 100);
            fontTitle.setFontHeight((short) 250);
            fontTitle.setColor(HSSFColor.BLACK.index);
            fontTitle.setFontHeightInPoints((short)12); //字体大小
            fontTitle.setBoldweight(fontTitle.BOLDWEIGHT_BOLD); //粗体

             
            
            // 创建单元格样式
            HSSFCellStyle styleTitle = wb.createCellStyle();
            styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            styleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            styleTitle.setFillForegroundColor(HSSFColor.GOLD.index);
            styleTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

           

            // 设置边框
            styleTitle.setBottomBorderColor(HSSFColor.GREY_40_PERCENT.index);
            styleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            styleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            
            styleTitle.setWrapText(false);
            styleTitle.setFont(fontTitle);// 设置字体
            
          
            //---------设置表体样式
            //设置字体样式
            HSSFFont fontBody = wb.createFont();
            fontBody.setFontName("微软雅黑");
            fontBody.setBoldweight((short) 100);
            fontBody.setFontHeight((short) 180);
            fontBody.setColor(HSSFColor.BLACK.index);
            
            // 创建单元格样式
            HSSFCellStyle styleBody = wb.createCellStyle();
            styleBody.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            styleBody.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

            //如果是通过则显示 绿色，如果不通过显示红色，否则显示正常颜色
            if(value.toLowerCase().equals("passed"))
            {
            styleBody.setFillForegroundColor(HSSFColor.GREEN.index);
            }
            else if(value.toLowerCase().equals("failed"))
            {
            styleBody.setFillForegroundColor(HSSFColor.RED.index);
            }
            else
            {
            styleBody.setFillForegroundColor(HSSFColor.WHITE.index);
            }
            styleBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
            
            
            // 设置边框
            styleBody.setBottomBorderColor(HSSFColor.GREY_40_PERCENT.index);
            styleBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            styleBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
            

            styleBody.setWrapText(true);
            styleBody.setFont(fontBody);// 设置字体
            
            // 合并单元格(startRow，endRow，startColumn，endColumn)
            //sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
            if(type.toLowerCase().equals("title"))
            {
            	return styleTitle;
            }
            else
            {
            	return styleBody;
            }
        }

}