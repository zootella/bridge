package bridge;

import org.json.JSONException;
import org.json.JSONObject;

public class Snippet {

	public static void snippet() {
		
		try {
			
			JSONObject o = new JSONObject();
			o.put("Hello", "you");
			
			o.put("Node", new JSONObject().put("beneath", "again"));
			
			String value = (String)o.get("Hello");
			
//			String buried = o.g.getString("Node")
			
			String s = o.toString();
			System.out.println(s);
			
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
}
