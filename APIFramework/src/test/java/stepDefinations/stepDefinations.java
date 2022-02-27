package stepDefinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinations extends Utils{
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data= new TestDataBuild();
	static String place_id;
	JsonPath js;
	
	@Given("Add Place Payload with {string}  {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
		 
		 res=given().spec(requestSpecification())
		 .body(data.addPlacePayLoad(name,language,address));

	    }

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    	APIResources resourceAPI=APIResources.valueOf(resource);
			//System.out.println(resourceAPI.getResource());
			
	    	 resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	     	
	    	if(method.equalsIgnoreCase("POST"))
	   		 response =res.when().post(resourceAPI.getResource());
	   		else if(method.equalsIgnoreCase("GET"))
	   			 response =res.when().get(resourceAPI.getResource());
	   		
	    }

	    @Then("^the API call got success with status code 200$")
	    public void the_api_call_got_success_with_status_code_200() throws Throwable {
	    	assertEquals(response.getStatusCode(),200);
	         
	    }

	    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	    public void something_in_response_body_is_something(String keyValue, String Expectedvalue) throws Throwable {
	       
	        assertEquals(getJsonPath(response,keyValue),Expectedvalue);
	    }
	    
	    @Then("verify place_Id created maps to {string} using {string}")
		public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
		
		
		     place_id=getJsonPath(response,"place_id");
			 res=given().spec(requestSpecification()).queryParam("place_id",place_id);
			 user_calls_with_http_request(resource,"GET");
			  String actualName=getJsonPath(response,"name");
			  assertEquals(actualName,expectedName);
			 
		    
		}
	    @Given("DeletePlace Payload")
	    public void deleteplace_Payload() throws IOException {
	        
	       
	    	res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	    }

}
