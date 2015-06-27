package com.ctrip.automation.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class GetValueFromHashMap {
	
	 Object objectTemp=new Object();	
	@SuppressWarnings("rawtypes")
	 HashMap mapTemp=new HashMap();
	//HashMap mapTempSub=new HashMap();
	@SuppressWarnings("rawtypes")
	 ArrayList listTemp=new ArrayList();
	public ArrayList getValue(String key,HashMap map)
	{ 	

		objectTemp=map.get(key);
		@SuppressWarnings("rawtypes")
		Iterator iter = map.entrySet().iterator();  
		while (iter.hasNext()) {  
		    @SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();  
		    Object val = entry.getValue();
		    
		    if(val.getClass().getName().equals("java.util.ArrayList"))
		    {
		    	@SuppressWarnings("rawtypes")
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
		    getValueSub(key,mapTemp);
		 
		} 
		
		return listTemp;
	}
	
	
	public void getValueSub(String key,HashMap mapSub)
	{ 
		//mapTempSub=map;
		objectTemp=mapSub.get(key);
		if(objectTemp!=null)
		{
		listTemp.add(objectTemp);
		}
		@SuppressWarnings("rawtypes")
		Iterator iter = mapSub.entrySet().iterator();  
		while (iter.hasNext()) {  
		    @SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();  
		    Object valSub = entry.getValue();
		    Object keySub=entry.getKey();
		    //System.out.println(keySub.toString()+"="+val.toString());
		    iter.remove();
		    mapTemp=mapSub;
		    if(valSub.getClass().getName().equals("java.util.ArrayList"))
		    {
		    	@SuppressWarnings("rawtypes")
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
