package com.ctrip.automation.result2map;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class JsonToHashMapFastJson
{
	
	//Featurs枚举值	缺省值	说明
	//QuoteFieldNames	TRUE	序列化输出含引号的字段
	//UseSingleQuotes	FALSE	使用单引号而不是双引号序列化
	//WriteMapNullValue	False	空值是否输出。大多数情况，值为null的属性输出是没有意义的，
	//缺省这个特性是打开的。
	//{'id':123,birthday : null}    <---区别--->         {"id":123}
//			
	//WriteEnumUsingToString	FALSE	Enum输出为枚举值还是枚举下标
	//UseISO8601DateFormat	FALSE	Date使用ISO8601格式输出日期
	//SkipTransientField	TRUE	如果是true，类中的Get方法对应的Field是transient，序列化时将会
	//WriteNullListAsEmpty	FALSE	list字段如果为null，输出为[]，而不是null
	//WriteNullNumberAsZero	FALSE	数值字段如果为null，输出为0，而不是null
	//WriteNullBooleanAsFalse	FALSE	Boolean字段如果为null，输出为false，而不是null
	//WriteNullStringAsEmpty	FALSE	字符类型字段如果为null，输出为""，而不是null
	//SortField	FALSE	按字段名称排序后输出
	//WriteTabAsSpecial	FALSE	把\t做转义输出(已过时)
	

//反序列化
  public static HashMap<String,Object> parseToMapJSONObject(String s)
  {
	  
	  HashMap<String, Object> map=JSON.parseObject(s, new TypeReference<HashMap<String,Object>>(){});
 	  return map;
  }
  
  
  @SuppressWarnings("unchecked")
public static HashMap<String,Object> jsonToHashMap(String s)
  {

	        @SuppressWarnings("rawtypes")
			HashMap jsonMap = JSON.parseObject(s, HashMap.class);
	 
	        HashMap<String, Object> resultMap = new HashMap<String, Object>();
	        for(Iterator iter = jsonMap.keySet().iterator(); iter.hasNext();){
	            String key = (String)iter.next();
	            if(jsonMap.get(key) instanceof JSONArray){
	                JSONArray jsonArray = (JSONArray)jsonMap.get(key);
	                @SuppressWarnings("rawtypes")
					List list = handleJSONArray(jsonArray);
	                resultMap.put(key, list);
	            }
	            else if(jsonMap.get(key) instanceof JSONObject){
	            	//如果是嵌套的json，则重复再次转换，否则直接存入MAP
	            	 resultMap.put(key, JSON.parseObject(jsonMap.get(key).toString(), HashMap.class));   
	            }
	            else{
	            	 resultMap.put(key, jsonMap.get(key));
	            }
	        }
	        return resultMap;
	}
	    @SuppressWarnings("unchecked")
		private static  List<HashMap<String, Object>> handleJSONArray(JSONArray jsonArray){
	        @SuppressWarnings("rawtypes")
			List list = new ArrayList();

	        for (Object object : jsonArray) {
	        	 
	        	HashMap<String, Object> map = new HashMap<String, Object>();
	        	if(object.toString().contains("{") && object.toString().contains("}") )
	        	{
	            JSONObject jsonObject = (JSONObject) object;
	            for (Entry<String, Object> entry : jsonObject.entrySet()) {
	                if(entry.getValue() instanceof  JSONArray){
	                    map.put((String)entry.getKey(), handleJSONArray((JSONArray)entry.getValue()));
	                }else{
	                    map.put((String)entry.getKey(), entry.getValue());
	                }
	            }
	            list.add(map);
	        	}
	            else
	            {
	             list.add(object.toString());
	            }
	        	 
	            }
	        return list;
	        }

  
  //序列化
  public static String toJsonString(Object map)
  {

	  String jsonString=JSON.toJSONString(map);
	  return jsonString;
  }
  
  //不带参数
  public static String toJsonString(Object map,boolean WriteNullValue)
  {
	  String jsonString="";
	  if(WriteNullValue)
	  {
		   SerializerFeature[] features={SerializerFeature.WriteMapNullValue}; 
		   jsonString=JSON.toJSONString(map,features);
	  }
	  else
	  {
		  SerializerFeature[] features={SerializerFeature.NotWriteDefaultValue};
		  jsonString=JSON.toJSONString(map,features);
	  }
	  
	  return jsonString;
  }
  
  
  //自定义参数
  public static String toJsonString(Object map,SerializerFeature [] features )
  {
	  String jsonString=JSON.toJSONString(map,features);
	  return jsonString;
	  
		
  }
 
}


