package com.ctrip.automation.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;


@SuppressWarnings("unused")
public class GetValueFromHashMap {
	
	 Object objectTemp=new Object();	
	 @SuppressWarnings("rawtypes")
	HashMap mapTemp=new HashMap();
	 @SuppressWarnings("rawtypes")
	ArrayList listTemp=new ArrayList();
	 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList getValue(String key,HashMap map)
	{ 	
		
		listTemp.clear();
		objectTemp=map.get(key);
		@SuppressWarnings("rawtypes")
		Iterator iter = map.entrySet().iterator();  
		while (iter.hasNext()) {  
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();  
		    Object val = entry.getValue();
		    
		    if(objectTemp != null)
		    {
		    		listTemp.add(objectTemp);
		    		objectTemp=null;
		    		//如果不为空，则保存值并清空
		    		
		    }
		    else if(val.getClass().getName().equals("java.util.ArrayList"))
		    {
				@SuppressWarnings("rawtypes")
				List list=(List) val;
				if(val.toString().contains("{") && val.toString().contains("}"))
				{
		    	for(@SuppressWarnings("rawtypes")
				Iterator ite=list.iterator();ite.hasNext();)
		    	{

		    		 getValueSub(key,(HashMap) ite.next()); 

		    	}
				}
		    }
		    
		    else if(val.getClass().getName().equals("java.util.HashMap"))
		    { 		
			 		getValueSub(key,(HashMap) val);
			 		
		    }
		   

		 
		}
		return listTemp;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  void  getValueSub(String key,HashMap mapSub)
	{ 
		//mapTempSub=map;
		objectTemp=mapSub.get(key);
		@SuppressWarnings("rawtypes")
		Iterator iterSub = mapSub.entrySet().iterator();  
		while (iterSub.hasNext()) 
		{  
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iterSub.next();  
		    Object valSub = entry.getValue();
		    Object keySub=entry.getKey();
		    //iterSub.remove();
		    //System.out.println(valSub);
		    mapTemp=mapSub;
		    if(objectTemp!=null)
			{
			listTemp.add(objectTemp);
			objectTemp=null;
			}
		    if(valSub.getClass().getName().equals("java.util.ArrayList"))
		    {
				@SuppressWarnings("rawtypes")
				List list=(List) valSub;
				if(valSub.toString().contains("{") && valSub.toString().contains("}"))
				{
		    	for(Iterator ite=list.iterator();ite.hasNext();)
		    	{
		    		getValueSub(key,(HashMap) ite.next());
	    		
		    	}
				}
		    }
		    if(valSub.getClass().getName().equals("java.util.HashMap"))
		    {		
			 		getValueSub(key,(HashMap) valSub);
			 		objectTemp=null;
		    }
			
		    
		}  
		 
	}

}
