package testAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import files.ReUsablemethod;
import files.payload;

import static io.restassured.RestAssured.*;

public class A2_Learning_Step2_Add_Body_Via_payLoad {
	static String getPlaceId=null;
	
	public void addPlaceAndCheckStatusCode(){
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().header("Content-Type", "application/json").queryParam("key", "qaclick123")
		.body(payload.addBody())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
	}
	
	public void addPlaceAndCheckParameterValue(){
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().header("Content-Type", "application/json").queryParam("key", "qaclick123")
		.body(payload.addBody())
		.when().post("maps/api/place/add/json")
		// This is used to check parameter value ".body("scope", equalTo("APP"))"
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")); 
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
		JsonPath jsGet = new JsonPath(getNewResponse); //for paring JSON
		return jsGet.get("address");
	}
	
	public void updateAddressWithPUT(){
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().header("Content-Type", "application/json").log().all().queryParam("key", "qaclick123")
		.body(payload.updateBody(addPlaceAndReturnGetPlaceId()))
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200);
	}
	
	public void updateAddressWithPUTAndCheckResponse(){
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String getResponse = given().header("Content-Type", "application/json").log().all().queryParam("key", "qaclick123")
		.body(payload.updateBody(addPlaceAndReturnGetPlaceId()))
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = new JsonPath(getResponse);
		Assert.assertEquals("Address successfully updated", js.get("msg"));
	}
	
	public void deleteRequest(){
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().header("Content-Type", "application/json").log().all().queryParam("key", "qaclick123")
		.body(payload.deleteRequestPayload(addPlaceAndReturnGetPlaceId()))
		.when().delete("maps/api/place/delete/json")
		.then().log().all().assertThat().statusCode(200);
	}
	
	public String addPlaceAndReturnGetPlaceId(){
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String getResponse = given().log().all().header("Content-Type", "application/json").queryParam("key", "qaclick123")
		.body(payload.addBody())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		return ReUsablemethod.rawToJsonAndGetParameterValue(getResponse, "place_id");
//		JsonPath js = new JsonPath(getResponse);
//		System.out.println("Place ID is ====>"+js.get("place_id").toString());
//		return js.get("place_id").toString();
	}
	
	public static void main(String[] args) {
		A2_Learning_Step2_Add_Body_Via_payLoad a1 = new A2_Learning_Step2_Add_Body_Via_payLoad();
//		a1.addPlaceAndCheckStatusCode();
//		a1.addPlaceAndCheckParameterValue();
		getPlaceId = a1.addPlaceAndReturnGetPlaceId();
		System.out.println("PlaceId is -->" +getPlaceId);
//		a1.getPlaceIdAndCheckStatusCode();
//		String newAddress = a1.getPlaceIdAndCheckAddress();
//		System.out.println("OLD Address -- > "+payload.Address );
//		System.out.println("New Address -- > "+newAddress );
//		Assert.assertEquals(Address, newAddress);
//		a1.updateAddressWithPUT();
//		a1.updateAddressWithPUTAndCheckResponse();
//		a1.deleteRequest();
	}
	
}
