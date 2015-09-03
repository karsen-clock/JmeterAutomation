package com.ctrip.automation.common;


import java.util.ArrayList;

public class SplitParamString
{
  public static String[] splitArray(String paramString, String sepator)
  {
    String param = paramString;
    String sepator1 = sepator;
    String[] paramArray = param.split(sepator1);
    return paramArray;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
public ArrayList splitList(String paramString, String demiliter1, String demiliter2)
  {
    ArrayList paramList = new ArrayList();
    ArrayList list1 = new ArrayList();
    ArrayList list2 = new ArrayList();

    String param = paramString;
    String sepator1 = demiliter1;
    String sepator2 = demiliter2;
    String[] tempArray = splitArray(param, sepator1);
    for (int i = 0; i < tempArray.length; i++)
    {
      list1.add(splitArray(tempArray[i], sepator2)[0].toString());
      list2.add(splitArray(tempArray[i], sepator2)[1].toString());
    }
    paramList.add(list1);
    paramList.add(list2);
    return paramList;
  }
}