import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ctrip.automation.result2map.HtmlToHashMap;
import com.ctrip.automation.result2map.JsonToHashMap;
import com.ctrip.automation.result2map.JsonToHashMapFastJson;


import com.alibaba.fastjson.parser.Feature;

import java.util.Map;

import org.json.JSONException;
 
public class Test1 {
     
    @SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, JSONException
    {
    	
    	
    	
//    	 String str = "welkes,sdlkwerj";  
//	        String regex = "(?<=s)(,)(?=s)";  
//	        String[] strs = str.split(regex);  
//	        for(int i = 0; i < strs.length; i++) {  
//	            System.out.printf("strs[%d] = %s%n", i, strs[i]);  
	            
	            
//                文件绝对路径改成你自己的文件路径
        FileReader fr=new FileReader("d:\\Users\\cdzhang\\Desktop\\json.txt");
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
        
        Feature[] ff={Feature.InitStringFieldAsEmpty};
        SerializerFeature[] features={SerializerFeature.WriteMapNullValue}; 
        
        JsonToHashMapFastJson js=new JsonToHashMapFastJson();
        System.out.println("hhele");
        HashMap<String,Object> hh=new HashMap<String,Object>();
        hh=js.jsonToHashMap(ss);
        hh.put("cctv"," ");
        System.out.println(hh);
        System.out.println(js.toJsonString(hh,true));
        
      
       //System.out.println(((HashMap<String,Object>) ((ArrayList) js.parserToMap(ss).get("payInfo")).get(0)).get("payInfoMain"));
       //System.out.println (((List) ((HashMap<String,Object>) js.parserToMap(ss).get("SearchOrderResult")).get("ItineraryList")).get(0));
        
        
        

    }
 
}

