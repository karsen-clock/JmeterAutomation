package com.ctrip.automation.result2map;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonToHashMap
{
  @SuppressWarnings({ "rawtypes", "unchecked" })
public static HashMap<String,Object> parserToMap(String s)
    throws JSONException
  {
    HashMap map = new HashMap();
    List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    JSONObject json = new JSONObject(s);
    Iterator keys = json.keys();
    while (keys.hasNext()) {
      String key = (String)keys.next();
      String value = json.get(key).toString();
//      System.out.println(key);
//      System.out.println(value);
      if ((value.startsWith("{")) && (value.endsWith("}")))
      {
        map.put(key, parserToMap(value));
      }
      else if((value.startsWith("[{")) && (value.endsWith("}]")))
      {
    	  String listString=value.substring(1, value.length()-1);
    	  System.out.println(listString);
    	  list=paraseToList(listString);
    	  map.put(key,list);
    	 
      }
      else {
        map.put(key, value);
      }
    }

    return map;
  }
  
  
  public static List paraseToList(String value)
  {
  	List list=new ArrayList<Object>();
    String [] array=value.split("(?<={)(?=})");
    for(String s:array)
    {
    	try {
			list.add(parserToMap(s));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 	System.out.println(list);
  	return list;
 
  }
}


