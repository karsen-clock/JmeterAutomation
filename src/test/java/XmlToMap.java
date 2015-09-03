import com.ctrip.automation.result2map.XmlToHashMap;
import com.ctrip.automation.common.GetValueFromHashMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;


public class XmlToMap {

	public static void main(String[] args) throws IOException, DocumentException {
		// TODO Auto-generated method stub
        FileReader fr=new FileReader("d:\\Users\\cdzhang\\Desktop\\test.txt");
        StringBuilder sb=new StringBuilder();
        String s="";
        //可以换成工程目录下的其他文本文件
        BufferedReader br=new BufferedReader(fr);
      
        while((s=br.readLine())!=null){
        	sb.append(s);
           
        }
        
        br.close();
        String ss=new String(sb.toString().getBytes(),"UTF-8");
        //ss=ss.substring(1, ss.length());
        //System.out.println("<RequestResult><?xml version=\"1.0\">");
        //ss=ss.replaceAll("<?xml version=\"1.0\">","<RequestResult>");
        System.out.println(ss);

XmlToHashMap xl=new XmlToHashMap();
GetValueFromHashMap getResponse=new GetValueFromHashMap();
HashMap map=new HashMap();
System.out.println( (HashMap) xl.dom2Map(ss));
ss=getResponse.getValue("RequestResult", (HashMap) xl.dom2Map(ss)).get(0).toString();
System.out.println((HashMap) xl.dom2Map(ss));
String sss=getResponse.getValue("TotalCount", (HashMap) xl.dom2Map(ss)).get(0).toString();
//System.out.println(getResponse.getValue("RequestResult", (HashMap) xl.dom2Map(ss)));
System.out.println(sss);

	}

}
