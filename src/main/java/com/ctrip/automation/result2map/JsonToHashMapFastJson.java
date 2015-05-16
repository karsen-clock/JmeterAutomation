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

public class JsonToHashMapFastJson
{

  public static HashMap<String,Object> paraseToMapJSONObject(String s)
  {
	 
	  
	  HashMap<String, Object> map=JSON.parseObject(s, new TypeReference<HashMap<String,Object>>(){});
	  System.out.println(map);

	 
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
			  System.out.println(list.size()+"------");
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
 
}


