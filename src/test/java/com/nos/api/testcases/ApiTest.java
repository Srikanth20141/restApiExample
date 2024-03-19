package com.nos.api.testcases;
import com.github.javafaker.Faker;	
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ApiTest {
	
	String bearer_token = "898d6dc2d36c9c062ea4fa564f019b450c4f24825e3d8c9551d25d0ff5ca7a47";
	Faker faker = new Faker();;
	String id;
	String baseUrl = "https://gorest.co.in/public/v2";
	
	@Test (enabled = true, priority = 0)
	public void createNewUser() {
		User user = new User(faker.name().firstName(),faker.internet().emailAddress(),"male","active");
		Response response = given().header("Authorization", "Bearer " + bearer_token).contentType(ContentType.JSON).body(user).when().log().all().post(baseUrl+"/users/");
		response.prettyPrint();
		id = response.jsonPath().getString("id");
		System.out.println(response.statusCode());
	}
	
	@Test (enabled = true, priority = 1)
	public void updateUserNewMethod() {
		User user = new User(faker.name().firstName(),faker.internet().emailAddress(),"male","active");
		Response response = given().header("Authorization", "Bearer " + bearer_token).pathParam("id", id).contentType(ContentType.JSON).body(user).when().log().all().put(baseUrl+"/users/{id}");
		response.prettyPrint();
		System.out.println(response.statusCode());
	}
	
	@Test (enabled = true, priority = 2)
	public void deleteUser() {
		Response response = given().header("Authorization", "Bearer " + bearer_token).pathParam("id", id).log().all().delete(baseUrl+"/users/{id}");
		System.out.println(response.statusCode());
	}
	
	@Test (enabled = true)
	public void getAllUser() {
		Response response = given().get(baseUrl+"/posts");
		response.prettyPrint();
		String jsonAsString = response.asString();
		System.out.println(jsonAsString);
	}

}
