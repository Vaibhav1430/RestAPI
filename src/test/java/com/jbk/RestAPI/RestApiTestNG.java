package com.jbk.RestAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class RestApiTestNG {

	final static String ROOT_URI12 = "http://restapi.demoqa.com/utilities/weather/city/Mumbai";

	@Test
	public void CheckWeatherDetails() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/Mumbai");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
	}

	@Test
	public void CheckWeatherDetailsStatus() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Mumbai");
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode , 200 , "Correct status code returned");
	}

	@Test
	public void CheckWeatherMessageBody() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Mumbai");
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		//System.out.println(bodyAsString);
		Assert.assertEquals(bodyAsString.contains("Mumbai"),true,"Response body contains Mumbai");
	}

	@Test
	public void CheckCityInJsonResponse() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Mumbai");
		JsonPath jsonPathEvaluator = response.jsonPath();
		String city = jsonPathEvaluator.get("City");
		System.out.println("City received from Response " + city);
		Assert.assertEquals(city, "Mumbai", "Correct city name received in the Response");
	}

	@Test
	public void CheckCityInJsonResponseJson() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Mumbai");
		JsonPath jsonPathEvaluator = response.jsonPath();
		String city = jsonPathEvaluator.get("City");
		System.out.println("City received from Response " + city);
		Assert.assertEquals(city, "Mumbai", "Correct city name received in the Response");
	}

	@Test
	public void CheckWeatherDetailsInvalidCity() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/1234767543");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode , 400, "Correct status code returned");
	}

	
}
