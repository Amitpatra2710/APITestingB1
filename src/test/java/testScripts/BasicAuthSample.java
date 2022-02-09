package testScripts;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class BasicAuthSample {
	
	@Test
	public void preemptiveAuth() {
		RestAssured.given().auth()
		.preemptive().basic("postman", "password")
		.when().get("https://postman-echo.com/basic-auth")
		.then().log().body();
	}
	
	@Test
	public void challengedAuth() {
		RestAssured.given().auth()
		.basic("postman", "password")
		.when().get("https://postman-echo.com/basic-auth")
		.then().log().body();
	}
	
	//002dddf25726aafa7992de22131d035c
	@Test
	public void getWeatherWithAPI() {
		RestAssured.given().queryParam("q", "Chennai")
		.queryParam("appid", "002dddf25726aafa7992de22131d035c")
		.when().get("https://api.openweathermap.org/data/2.5/weather")
		.then().log().body();
		
	}
	
	@Test
	public void validateCountry() {
		String strCountry = RestAssured.given().queryParam("q", "Chennai")
		.queryParam("appid", "002dddf25726aafa7992de22131d035c")
		.when().get("https://api.openweathermap.org/data/2.5/weather")
		.then().extract().path("sys.country");
		Assert.assertTrue(strCountry.equals("IN"));
		
	}
	
	@Test
	public void getWeather() {
		String mainWeather = RestAssured.given().queryParam("q", "Chennai")
		.queryParam("appid", "002dddf25726aafa7992de22131d035c")
		.when().get("https://api.openweathermap.org/data/2.5/weather")
		.then().extract().path("weather[0].main");
		System.out.println("Main Weather is :" +mainWeather);
		
	}
	

}
