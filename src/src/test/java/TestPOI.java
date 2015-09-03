import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import com.ctrip.automation.common.ExcelWriter;
import com.ctrip.automation.common.ExcelReader;


public class TestPOI {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ExcelWriter excel=new ExcelWriter();
		String fullFilePath="C://hello.xls";
		String sheetName="Test";
		List value=new ArrayList();
		value.add("测试编号");
		value.add("状态");
		value.add("执行时间");
		value.add("备注");
		String styleType="title";
		List value2=new ArrayList();
		value2.add("act002");
		value2.add("failed");
		value2.add("2015-8-1");
		value2.add("原因未知");
		String styleType2="body";
		//excel.CreateExcelFile(fullFilePath, sheetName, value, styleType);
		//excel.CreateExcelFile(fullFilePath, sheetName, value2, styleType2);
	
		ExcelReader reader=new ExcelReader();
		String [] xx=reader.readExcelTitle("C://hello.xls", 1, 0);
		System.out.println(xx[0]+"-"+xx[1]+xx[2]+xx[3]);
		System.out.println(reader.readExcelContent("C://hello.xls", 1, 0));
		
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String ss=formatter.format(new Date());
		 System.out.println(ss);
		 

	}

}
