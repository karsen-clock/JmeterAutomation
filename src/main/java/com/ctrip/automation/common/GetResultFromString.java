package com.ctrip.automation.common;



public class GetResultFromString
{
  public String[] getArray(String sourceString, String separator)
  {
    String[] splitResult = sourceString.split(separator);
    return splitResult;
  }
}