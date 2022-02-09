package testScripts;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostRequestSample {
	//name, email, gender, status
//	@Test
	public void postUser() {
		//RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
		RestAssured.baseURI = "https://reqres.in/api/users";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "TestUser");
		jsonObject.put("job", "TestArchitect");
//		jsonObject.put("email", "testuser@gmail.com");
//		jsonObject.put("gender", "male");
//		jsonObject.put("status", "Active");
		System.out.println(jsonObject.toString());
		Response resp = RestAssured.given()
				.accept(ContentType.JSON)
				.contentType("application/JSON")
				.and()
				.body(jsonObject.toString())
				.post();		
		System.out.println("Status Code :" +resp.getStatusCode());
	}
	
	//name, email, gender, status
		@Test
		public void postUserWithAuth() {
			RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
			//RestAssured.baseURI = "https://reqres.in/api/users";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", "TestUser");
			jsonObject.put("email", "testuser1234@gmail.com");
			jsonObject.put("gender", "male");
			jsonObject.put("status", "Active");
			System.out.println(jsonObject.toString());
			Response resp = RestAssured.given()
					.header("authorization", "Bearer d2244caf8ab5f361353aea72a8ea77a7709ed0e01c1b6556612ffb83d3475047")
					.accept(ContentType.JSON)
					.contentType("application/JSON")
					.and()
					.body(jsonObject.toString())
					.post();		
			System.out.println("Status Code :" +resp.getStatusCode());
			System.out.println(resp.asString());
		}

}
