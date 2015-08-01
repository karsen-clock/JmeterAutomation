import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ctrip.automation.common.OperateExcel;

public class TestPOI {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		OperateExcel excel=new OperateExcel();
		String fullFilePath="C:\\hello.xls";
		String sheetName="Test";
		List value=new ArrayList();
		value.add("act002");
		value.add("failed");
		value.add("2015-8-1");
		value.add("原因未知");
		String styleType="body";
		excel.CreateExcelFile(fullFilePath, sheetName, value, styleType);
	}

}
