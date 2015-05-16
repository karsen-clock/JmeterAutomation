package com.ctrip.automation.result2map;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;



import java.util.Map.Entry;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
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
  public static HashMap<String,Object> paraseToMapJSONObject(String s)
  {
	  
	  HashMap<String, Object> map=JSON.parseObject(s, new TypeReference<HashMap<String,Object>>(){});

	 
	  return map;
  }
  
  
  @SuppressWarnings("unchecked")
public static HashMap<String,Object> paraseToMap(String s)
  {





	  
	  HashMap<String,Object> mapResult=new HashMap<String,Object>();
	  mapResult=paraseToMapJSONObject(s);
	  List hashMapList=new ArrayList();
	  
	  Iterator iter = mapResult.entrySet().iterator();
	  while(iter.hasNext())
	  {
		  Entry entry=(Entry) iter.next();
		  String key=entry.getKey().toString();
		  Object value=entry.getValue();

		  //如果是jsonarray则保存在list中并修改map的value
		  if(value.getClass().getName()=="com.alibaba.fastjson.JSONArray")
		  {
			  JSONArray list=JSON.parseArray(value.toString());
			 
			  Iterator listIterator=list.iterator();
			  
			  while(listIterator.hasNext())
			  {
				  

				  hashMapList.add(paraseToMap(listIterator.next().toString()));
				
			  }
			  mapResult.put(key, hashMapList);
		  }
		  //如果是JSONObject则直接修改map
		  else if(value.getClass().getName()=="com.alibaba.fastjson.JSONObject")
		  {
			  mapResult.put(key, paraseToMapJSONObject(value.toString()));
		  }

	  }
	  return mapResult;
  }
  
  //序列化
  public static String toJsonString(Object map)
  {

	  String jsonString=JSON.toJSONString(map);
	  return jsonString;
  }
  
  //不带参数
  public static String toJsonString(Object map,SerializerFeature[] features)
  {

	  String jsonString=JSON.toJSONString(map,features);
	  return jsonString;
  }
 
}


