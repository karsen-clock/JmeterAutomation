package com.ctrip.automation.result2map;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

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
      else if((value.startsWith("[")) && (value.endsWith("]")))
      {
    	  JSONArray ja=new JSONArray(value);
    	  list=paraseToList(ja);

    	  map.put(key,list);
    	 
      }
      else {
        map.put(key, value);
      }
    }

    return map;
  }
  
  
  public static HashMap<String,Object> parserToMap(String s,Boolean lite)
		    throws JSONException
		  {
		    HashMap map = new HashMap();
		    List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		    JSONObject json = new JSONObject(s);
		    Iterator keys = json.keys();
		    while (keys.hasNext()) {
		      String key = (String)keys.next();
		      String value = json.get(key).toString();
//		      System.out.println(key);
//		      System.out.println(value);
		      if ((value.startsWith("{")) && (value.endsWith("}")))
		      {
		        map.put(key, parserToMap(value));
		      }
		      else if((value.startsWith("[")) && (value.endsWith("]")))
		      {
		    	  JSONArray ja=new JSONArray(value);
		    	  list=paraseToList(ja);

		    	  map.put(key,list);
		    	 
		      }
		      else if(lite==false ||(lite==true && !value.equals("")))
		      {
		        map.put(key, value);
		      }
		    }

		    return map;
		  }
  
  public static List<Map<String, Object>> paraseToList(JSONArray jsonArray )  
  {
  	List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    for(int i=0;i<jsonArray.length();i++)
    {

    	try {
			list.add(parserToMap(jsonArray.getString(i)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

  	return list;
 
  }
}


