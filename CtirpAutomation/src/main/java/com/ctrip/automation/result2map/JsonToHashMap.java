package com.ctrip.automation.result2map;


import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonToHashMap
{
  @SuppressWarnings({ "rawtypes", "unchecked" })
public static HashMap parserToMap(String s)
    throws JSONException
  {
    HashMap map = new HashMap();
    JSONObject json = new JSONObject(s);
    Iterator keys = json.keys();
    while (keys.hasNext()) {
      String key = (String)keys.next();
      String value = json.get(key).toString();
      if ((value.startsWith("{")) && (value.endsWith("}")))
        map.put(key, parserToMap(value));
      else {
        map.put(key, value);
      }
    }

    return map;
  }
}