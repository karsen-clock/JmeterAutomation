import java.util.HashMap;

//import org.json.*;





import com.alibaba.fastjson.JSONException;
import com.ctrip.automation.result2map.JsonToHashMapFastJson;
public class MapToJsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("test", "123");
		map.put("cctv", "");
		System.out.println(map);
		JsonToHashMapFastJson json=new JsonToHashMapFastJson();
		System.out.println(json);
		JsonToHashMapFastJson ps=new JsonToHashMapFastJson();
		
		try {
			System.out.println(ps.toJsonString(json.toString(), true));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
