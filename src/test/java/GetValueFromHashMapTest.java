import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.ctrip.automation.common.GetValueFromHashMap;
import com.ctrip.automation.result2map.JsonToHashMapFastJson;
import com.ctrip.automation.result2map.XmlToHashMap;




import org.dom4j.DocumentException;
import org.json.JSONException;
 
public class GetValueFromHashMapTest {
     
    @SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, JSONException, DocumentException
    {
    	

//    	 String str = "welkes,sdlkwerj";  
//	        String regex = "(?<=s)(,)(?=s)";  
//	        String[] strs = str.split(regex);  
//	        for(int i = 0; i < strs.length; i++) {  
//	            System.out.printf("strs[%d] = %s%n", i, strs[i]);  
	            
	            
//                文件绝对路径改成你自己的文件路径
        FileReader fr=new FileReader("d:\\Users\\cdzhang\\Desktop\\xml.txt");
        StringBuilder sb=new StringBuilder();
        String s="";
        //可以换成工程目录下的其他文本文件
        BufferedReader br=new BufferedReader(fr);
      
        while((s=br.readLine())!=null){
        	sb.append(s);
           
        }
        
        br.close();
        String ss=new String(sb.toString().getBytes(),"UTF-8");
        
    	 
    	XmlToHashMap ss2=new XmlToHashMap();
    	Map  sss=ss2.dom2Map(ss);
    	
    	GetValueFromHashMap getResult=new GetValueFromHashMap();
    	System.out.println(sss);
        System.out.println(getResult.getValue("PayType", (HashMap) sss));
        System.out.println(sss);
        System.out.println(getResult.getValue("RoomID", (HashMap) sss));
       // JsonToHashMapFastJson js=new JsonToHashMapFastJson();
        //HashMap<String,Object> hh=new HashMap<String,Object>();
        //GetValueFromHashMap hashMapvalue=new GetValueFromHashMap();
        //hh=js.jsonToHashMap(ss);
       // System.out.println(hashMapvalue.getValue("payInfo",hh));
        
      
       //System.out.println(((HashMap<String,Object>) ((ArrayList) js.parserToMap(ss).get("payInfo")).get(0)).get("payInfoMain"));
       //System.out.println (((List) ((HashMap<String,Object>) js.parserToMap(ss).get("SearchOrderResult")).get("ItineraryList")).get(0));
        

    }
 
}

