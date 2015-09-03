import com.ctrip.automation.result2map.XmlToHashMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.dom4j.DocumentException;


public class XmlToMap {

	public static void main(String[] args) throws IOException, DocumentException {
		// TODO Auto-generated method stub
        FileReader fr=new FileReader("C:\\Users\\hasee\\Desktop\\test.txt");
        StringBuilder sb=new StringBuilder();
        String s="";
        //可以换成工程目录下的其他文本文件
        BufferedReader br=new BufferedReader(fr);
      
        while((s=br.readLine())!=null){
        	sb.append(s);
           
        }
        
        br.close();
        String ss=new String(sb.toString().getBytes(),"UTF-8");
System.out.println(ss);

XmlToHashMap xl=new XmlToHashMap();
System.out.println(xl.dom2Map(ss));
	}

}
