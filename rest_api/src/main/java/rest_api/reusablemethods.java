package rest_api;

import io.restassured.path.json.JsonPath;

public class reusablemethods {
 
	public static JsonPath rawtoJson(String response) {
		JsonPath js= new JsonPath(response);
		return js;
	}

}
