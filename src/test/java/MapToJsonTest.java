import java.util.HashMap;

import org.json.*;


import com.ctrip.automation.result2map.JsonToHashMap;
public class MapToJsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("test", "123");
		map.put("cctv", "");
		System.out.println(map);
		JSONObject json=new JSONObject(map);
		System.out.println(json);
		JsonToHashMap ps=new JsonToHashMap();
		
		try {
			System.out.println(ps.jsonToHashMap(json.toString(), true));
			JSONObject json2 = new JSONObject(ps.jsonToHashMap(json.toString(), true));
			System.out.println(json2.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
