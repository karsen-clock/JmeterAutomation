package com.ctrip.automation.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GetValueFromHashMap {
	
	Object objectTemp=new Object();	
	public String getValue(String key,HashMap map)
	{ 

		objectTemp=map.get(key);
		Iterator iter = map.entrySet().iterator();  
		while (iter.hasNext() && objectTemp==null) {  
		    Map.Entry entry = (Map.Entry) iter.next();  
		    Object val = entry.getValue();
		    getChildNode(key,val);
		} 
		
		return objectTemp.toString();
	}
	
	public void getChildNode(String key,Object val)
	{
		 if(val.getClass().getName().equals("java.util.ArrayList"))
		    {
		    	List list=(List) val;
		    	for(Iterator ite=list.iterator();ite.hasNext();)
		    	{
		    		getValue(key,(HashMap) ite.next());
		    	}
		    }
		 if(val.getClass().getName().equals("java.util.HashMap"))
		    {
		    		getValue(key,(HashMap) val);
		    }
	}

}
