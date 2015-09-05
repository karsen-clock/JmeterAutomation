package com.ctrip.automation.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelReader {

	    private POIFSFileSystem fs;
	    private HSSFWorkbook wb;
	    private HSSFSheet sheet;
	    private HSSFRow row;

	    /**
	     * 读取Excel表格表头的内容
	     * @param InputStream
	     * @return String 表头内容的数组
	     */
	    @SuppressWarnings("deprecation")
		public String[] readExcelTitle(String filePath,String sheetName,int rowIndex) {
	    	InputStream is = null;
	    	try {
	            // 对读取Excel表格标题测试
	            is= new FileInputStream(filePath);
	            ExcelReader excelReader = new ExcelReader();
	            String[] title ;
	    	
	        } catch (FileNotFoundException e) {
	            System.out.println("未找到指定路径的文件!");
	            e.printStackTrace();
	        }
	            
	            
	    	try {
	            fs = new POIFSFileSystem(is);
	            wb = new HSSFWorkbook(fs);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    	
	        sheet = wb.getSheet(sheetName);
	        row = sheet.getRow(rowIndex);
	        // 标题总列数
	        int colNum = row.getPhysicalNumberOfCells();
	        System.out.println("colNum:" + colNum);
	        String[] title = new String[colNum];
	        for (int i = 0; i < colNum; i++) {
	            //title[i] = getStringCellValue(row.getCell((short) i));
	            title[i] = getCellFormatValue(row.getCell(i));
	        }

	        return title;
	    }

	    /**
	     * 读取Excel数据内容
	     * @param InputStream
	     * @return Map 包含单元格数据内容的Map对象
	     */
	    
		public List readExcelContent(String filePath,String sheetName,int rowIndex) {
	    	   InputStream is=null;
	    	try {
	            // 对读取Excel表格标题测试
	            is = new FileInputStream(filePath);
	            ExcelReader excelReader = new ExcelReader();
	            String[] title ;
	    	
	        } catch (FileNotFoundException e) {
	            System.out.println("未找到指定路径的文件!");
	            e.printStackTrace();
	        }
	           
	    	

	    
	        String str = "";
	        List list=new ArrayList();
	        String [] title=readExcelTitle(filePath,sheetName,0);
	        
	        try {
	            fs = new POIFSFileSystem(is);
	            wb = new HSSFWorkbook(fs);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        sheet = wb.getSheet(sheetName);
	        // 得到总行数
	        int rowNum = sheet.getLastRowNum();
	        row = sheet.getRow(rowIndex);
	        int colNum = row.getPhysicalNumberOfCells();
	        // 正文内容应该从第二行开始,第一行为表头的标题
	        for (int i = 1; i <= rowNum; i++) {
	            row = sheet.getRow(i);
	            int j = 0;
	            IdentityHashMap<String,String> content = new IdentityHashMap<String, String>();
	            while (j < colNum) {
	                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
	                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
	                // str += getStringCellValue(row.getCell((short) j)).trim() +
	                // "-";
	                str += getCellFormatValue(row.getCell(j)).trim() + "    ";
		            content.put(title[j].toString(), str);
		           
		            str = "";
	                j++;
	            }
	            list.add(content);
	            //将标题作为 KEY,body作为内容存入MAP

	            
	        }
	        return list;
	    }
		
		//获取指定行元素个数
		public int [] getRowCellsNum(String filePath,String sheetName,int rowIndex) {
	    	   InputStream is=null;
	    	try {
	            // 对读取Excel表格标题测试
	            is = new FileInputStream(filePath);
	            ExcelReader excelReader = new ExcelReader();
	            String[] title ;
	    	
	        } catch (FileNotFoundException e) {
	            System.out.println("未找到指定路径的文件!");
	            e.printStackTrace();
	        }
	           
	    	
	        
	        try {
	            fs = new POIFSFileSystem(is);
	            wb = new HSSFWorkbook(fs);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        sheet = wb.getSheet(sheetName);
	        // 得到总行数
	        int rowNum = sheet.getLastRowNum();
	        row = sheet.getRow(rowIndex);
	        int colNum = row.getPhysicalNumberOfCells();
	        int  result []={rowNum,colNum};
	        return result;
	    }

	    /**
	     * 获取单元格数据内容为字符串类型的数据
	     * 
	     * @param cell Excel单元格
	     * @return String 单元格数据内容
	     */
	    @SuppressWarnings("unused")
		private String getStringCellValue(HSSFCell cell) {
	        String strCell = "";
	        switch (cell.getCellType()) {
	        case HSSFCell.CELL_TYPE_STRING:
	            strCell = cell.getStringCellValue();
	            break;
	        case HSSFCell.CELL_TYPE_NUMERIC:
	            strCell = String.valueOf(cell.getNumericCellValue());
	            break;
	        case HSSFCell.CELL_TYPE_BOOLEAN:
	            strCell = String.valueOf(cell.getBooleanCellValue());
	            break;
	        case HSSFCell.CELL_TYPE_BLANK:
	            strCell = "";
	            break;
	        default:
	            strCell = "";
	            break;
	        }
	        if (strCell.equals("") || strCell == null) {
	            return "";
	        }
	        if (cell == null) {
	            return "";
	        }
	        return strCell;
	    }

	    /**
	     * 获取单元格数据内容为日期类型的数据
	     * 
	     * @param cell
	     *            Excel单元格
	     * @return String 单元格数据内容
	     */
//	    private String getDateCellValue(HSSFCell cell) {
//	        String result = "";
//	        try {
//	            int cellType = cell.getCellType();
//	            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
//	                Date date = cell.getDateCellValue();
//	                Calendar calendar = Calendar.getInstance();
//	                result = (calendar.get(Calendar.YEAR)) + "-" + (calendar.get(Calendar.MONDAY) + 1)
//	                        + "-" + calendar.get(Calendar.DATE);
//	            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
//	                String date = getStringCellValue(cell);
//	                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
//	            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
//	                result = "";
//	            }
//	        } catch (Exception e) {
//	            System.out.println("日期格式不正确!");
//	            e.printStackTrace();
//	        }
//	        return result;
//	    }

	    /**
	     * 根据HSSFCell类型设置数据
	     * @param cell
	     * @return
	     */
	    private String getCellFormatValue(HSSFCell cell) {
	        String cellvalue = "";
	        if (cell != null) {
	            // 判断当前Cell的Type
	            switch (cell.getCellType()) {
	            // 如果当前Cell的Type为NUMERIC
	            case HSSFCell.CELL_TYPE_NUMERIC:
	            case HSSFCell.CELL_TYPE_FORMULA: {
	                // 判断当前的cell是否为Date
	                if (HSSFDateUtil.isCellDateFormatted(cell)) {
	                    // 如果是Date类型则，转化为Data格式
	                    
	                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
	                    //cellvalue = cell.getDateCellValue().toLocaleString();
	                    
	                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
	                    Date date = cell.getDateCellValue();
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                    cellvalue = sdf.format(date);
	                    
	                }
	                // 如果是纯数字
	                else {
	                    // 取得当前Cell的数值
	                    cellvalue = String.valueOf(cell.getNumericCellValue());
	                }
	                break;
	            }
	            // 如果当前Cell的Type为STRIN
	            case HSSFCell.CELL_TYPE_STRING:
	                // 取得当前的Cell字符串
	                cellvalue = cell.getRichStringCellValue().getString();
	                break;
	            // 默认的Cell值
	            default:
	                cellvalue = " ";
	            }
	        } else {
	            cellvalue = "";
	        }
	        return cellvalue;

	    }
}
