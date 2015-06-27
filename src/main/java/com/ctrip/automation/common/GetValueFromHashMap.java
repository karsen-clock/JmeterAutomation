package com.ctrip.automation.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class GetValueFromHashMap {
	
	 Object objectTemp=new Object();	
	 HashMap mapTemp=new HashMap();
	 ArrayList listTemp=new ArrayList();
	 
	public ArrayList getValue(String key,HashMap map)
	{ 	
		
		listTemp.clear();
		objectTemp=map.get(key);
		Iterator iter = map.entrySet().iterator();  
		while (iter.hasNext()) {  
			Map.Entry entry = (Map.Entry) iter.next();  
		    Object val = entry.getValue();
		    
		    if(val.getClass().getName().equals("java.util.ArrayList"))
		    {
				List list=(List) val;
		    	for(Iterator ite=list.iterator();ite.hasNext();)
		    	{
		    		getValueSub(key,(HashMap) ite.next());
		    	}
		    }
		    if(val.getClass().getName().equals("java.util.HashMap"))
		    {
			 		
			 		getValueSub(key,(HashMap) val);
		    }
		   
		    //getValueSub(key,mapTemp);
		 
		}
		return listTemp;
	}
	
	
	public  void  getValueSub(String key,HashMap mapSub)
	{ 
		//mapTempSub=map;
		objectTemp=mapSub.get(key);
		if(objectTemp!=null)
		{
		listTemp.add(objectTemp);
		System.out.println(listTemp);
		}
		Iterator iterSub = mapSub.entrySet().iterator();  
		while (iterSub.hasNext()) 
		{  
			Map.Entry entry = (Map.Entry) iterSub.next();  
		    Object valSub = entry.getValue();
		    Object keySub=entry.getKey();
		    //iterSub.remove();
		    
		    mapTemp=mapSub;
		    if(valSub.getClass().getName().equals("java.util.ArrayList"))
		    {
				List list=(List) valSub;
		    	for(Iterator ite=list.iterator();ite.hasNext();)
		    	{
		    		getValueSub(key,(HashMap) ite.next());
		    	}
		    }
		    if(valSub.getClass().getName().equals("java.util.HashMap"))
		    {		
			 		getValueSub(key,(HashMap) valSub);
		    }
		    
		}  
		 
	}

}
