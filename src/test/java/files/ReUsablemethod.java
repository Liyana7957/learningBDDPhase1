package files;

import io.restassured.path.json.JsonPath;

public class ReUsablemethod {
	
	public static String rawToJsonAndGetParameterValue(String response, String parameterValue){
		JsonPath js = new JsonPath(response);
		return js.get(parameterValue).toString();
	}
	
	public static JsonPath rawToJson(String response){
		JsonPath js = new JsonPath(response);
		return js;
	}

}
