package com.ctrip.automation.result2map;


import java.util.HashMap;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlToHashMap
{
  @SuppressWarnings({ "rawtypes", "unchecked" })
public static HashMap toHashMap(String htmlString)
  {
    Document doc = Jsoup.parse(htmlString);
    Elements elements = doc.getElementsByTag("input");

    HashMap map = new HashMap();
    Iterator iterator = elements.iterator();
    while (iterator.hasNext())
    {
      Element e = (Element)iterator.next();
      String name = e.attr("name");
      String value = e.attr("value");
      String type = e.attr("type");
      if ((!type.equals("button")) && (!type.equals("image")) && (!type.equals("reset")) && (!type.equals("submit")))
      {
        if (!name.isEmpty())
        {
          map.put(name, value);
        }
      }
    }
    return map;
  }
}