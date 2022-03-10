package testAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class A1_Learning_Step1_AddPlaceAPI {
	static String getPlaceId=null;
	static String Address = "29, side layout, cohen 09";
	
	public void addPlaceAndCheckStatusCode(){
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().header("Content-Type", "application/json").queryParam("key", "qaclick123")
		.body("{\r\n" + 
				"	\"location\": {\r\n" + 
				"	\"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"             },\r\n" + 
				"\"accuracy\": 50,\r\n" + 
				"\"name\": \"Frontline house\",\r\n" + 
				"\"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"\"address\": \""+Address+"\",\r\n" + 
				"\"types\": [\"shoe park\",\"shop\" ],\r\n" + 
				"\"website\":\"http://google.com\",\r\n" + 
				"\"language\": \"French-IN\"\r\n" + 
				"}")
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
	}
	
	public void addPlaceAndCheckParameterValue(){
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().header("Content-Type", "application/json").queryParam("key", "qaclick123")
		.body("{\r\n" + 
				"	\"location\": {\r\n" + 
				"	\"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"             },\r\n" + 
				"\"accuracy\": 50,\r\n" + 
				"\"name\": \"Frontline house\",\r\n" + 
				"\"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"\"address\": \" "+Address+"\",\r\n" + 
				"\"types\": [\"shoe park\",\"shop\" ],\r\n" + 
				"\"website\":\"http://google.com\",\r\n" + 
				"\"language\": \"French-IN\"\r\n" + 
				"}")
		.when().post("maps/api/place/add/json")
		// This is used to check parameter value ".body("scope", equalTo("APP"))"
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")); 
	}
	
	public String addPlaceAndReturnGetPlaceId(){
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String getResponse = given().log().all().header("Content-Type", "application/json").queryParam("key", "qaclick123")
		.body("{\r\n" + 
				"	\"location\": {\r\n" + 
				"	\"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"             },\r\n" + 
				"\"accuracy\": 50,\r\n" + 
				"\"name\": \"Frontline house\",\r\n" + 
				"\"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"\"address\": \""+Address+"\",\r\n" + 
				"\"types\": [\"shoe park\",\"shop\" ],\r\n" + 
				"\"website\":\"http://google.com\",\r\n" + 
				"\"language\": \"French-IN\"\r\n" + 
				"}")
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = new JsonPath(getResponse);
		return js.get("place_id").toString();
	}
	
	public void getPlaceIdAndCheckStatusCode(){
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().header("Content-Type", "application/json").queryParam("key", "qaclick123")
		// here we pass place Id via method "addPlaceAndReturnGetPlaceId"
		.queryParam("place_id", getPlaceId)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200);
	}
	
	
	public String getPlaceIdAndCheckAddress(){
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String getNewResponse = given().log().all().header("Content-Type", "application/json").queryParam("key", "qaclick123")
		// here we pass place Id via method "addPlaceAndReturnGetPlaceId"
		.queryParam("place_id", getPlaceId)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath jsGet = new JsonPath(getNewResponse);
		return jsGet.get("address");
	}
	
	public static void main(String[] args) {
		A1_Learning_Step1_AddPlaceAPI a1 = new A1_Learning_Step1_AddPlaceAPI();
		//a1.addPlaceAndCheckStatusCode();
		//a1.addPlaceAndCheckParameterValue();
		getPlaceId = a1.addPlaceAndReturnGetPlaceId();
		System.out.println("PlaceId is -->" +getPlaceId);
		//a1.getPlaceIdAndCheckStatusCode();
		String newAddress = a1.getPlaceIdAndCheckAddress();
		System.out.println("OLD Address -- > "+Address );
		System.out.println("New Address -- > "+newAddress );
		Assert.assertEquals(Address, newAddress);
	}
	
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("PlaceId", js.get("place_id").toString());
//	map.put("Address", js.get("place_id").toString());

}
