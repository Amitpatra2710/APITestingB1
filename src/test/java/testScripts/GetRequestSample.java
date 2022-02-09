package testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetRequestSample {

	@Test
	public void testResponseStatus() {
		RequestSpecification request = RestAssured.given();
		request.baseUri("https://demoqa.com/utilities/weather/city");
		Response response = request.get("/Chennai");
		//String resString = response.asString();
		String resString = response.asPrettyString();
		System.out.println("Response Details: " +resString);
		System.out.println("Response Headers: " +response.contentType());
		System.out.println("Status Code: " +response.statusCode());
		ValidatableResponse valRes =response.then();
		valRes.statusCode(200);
		Headers allheaders = response.headers();
		for(Header header: allheaders) {
			System.out.println("Key :"+header.getName()+" Value: "+header.getValue());
		}
		String ServerType = response.header("Server");
		Assert.assertEquals(ServerType, "nginx/1.17.10 (Ubuntu)");
		//String DateValue = response.header("Date");
		//Assert.assertEquals(DateValue, "Fri, 04 Feb 2022 11:59:07 GMT");
		//Assert.assertEquals(resString.toLowerCase().contains("chennai"), true);
		JsonPath jsonOathEval = response.jsonPath();
		System.out.println("City name :" +jsonOathEval.get("City"));
		String cityName = jsonOathEval.get("City");
		Assert.assertEquals(cityName, "Chennai", "Expected City name is fetched");
	}
	
	@Test
	public void testResponseBDD() {
		RestAssured.given()
		.baseUri("https://demoqa.com/utilities/weather/city")
		.when().get("/Chennai")
		.then()
		.statusCode(200);
	}
}
