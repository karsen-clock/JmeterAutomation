package com.ctrip.automation.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class GetValueFromHashMap {
	
	Object objectTemp=new Object();	
	public String getValue(String key,@SuppressWarnings("rawtypes") HashMap map)
	{ 

		objectTemp=map.get(key);
		@SuppressWarnings("rawtypes")
		Iterator iter = map.entrySet().iterator();  
		while (iter.hasNext() && objectTemp==null) {  
		    @SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();  
		    Object val = entry.getValue();
		    getChildNode(key,val);
		} 
		
		return objectTemp.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public void getChildNode(String key,Object val)
	{
		 if(val.getClass().getName().equals("java.util.ArrayList"))
		    {
		    	@SuppressWarnings("rawtypes")
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
