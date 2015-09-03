package com.ctrip.automation.common;
import java.io.*;
import java.util.List;
@SuppressWarnings("unused")
public class WriteObjectToTxt {
	public  boolean writeObject(String result,String fileName) throws IOException
	{
	try{      
				
				BufferedWriter bW=new BufferedWriter(new FileWriter(fileName));
				bW.write(result);
				bW.newLine();
				bW.close();
				System.out.println("write successfully");
				return true;
	}
	catch(FileNotFoundException e)
	{  
		System.out.println("write failed");
		return false;
	}
	}
}
