import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.ctrip.automation.result2map.JsonToHashMap;
import java.util.HashMap;
 
public class Test1 {
     
    public static void main(String[] args) throws IOException
    {
                //文件绝对路径改成你自己的文件路径
        FileReader fr=new FileReader("C:\\Users\\hasee\\Desktop\\json.txt");
        StringBuilder sb=new StringBuilder();
        String s="";
        //可以换成工程目录下的其他文本文件
        BufferedReader br=new BufferedReader(fr);
        System.out.println("test");
        while((s=br.readLine())!=null){
        	sb.append(s + "\n");
           
        }
        br.close();
        //String ss=sb.toString();
        //System.out.println(ss);
        
        
        //HashMap responseMap=new HashMap();
        //JsonToHashMap js=new JsonToHashMap();

        //responseMap=js.parserToMap(ss);
       // System.out.println(responseMap.get("result"));

    }
 
}
