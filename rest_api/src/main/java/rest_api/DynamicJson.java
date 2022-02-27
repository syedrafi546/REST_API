package rest_api;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test
	public void addBook() {
		RestAssured.baseURI="http://216.10.245.166";
		String response= given().log().all().header("Content-Type","application/json").body(payload.addbook("raaaafi","6446")).
		when().post("/Library/Addbook.php").then().log().all().assertThat().extract().response().asString();
		JsonPath js = reusablemethods.rawtoJson(response);
		String id= js.get("ID");
		System.out.println(id);
	}
	

}
