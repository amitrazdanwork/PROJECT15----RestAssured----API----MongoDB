package com.qa.endpoints;

import org.testng.annotations.Test;

import com.qa.payloads.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserEndpoints {

	/* Define CRUD methods implementation common for all APIs of User entity */
	public static Response createUser(User payload) {
        
	Response response =     given()
		                        .contentType(ContentType.JSON)
		                        .header("api_key", "amit_password")
		                        .accept(ContentType.JSON)
		                        .body(payload)
	                       .when()
	                            .post(Routes.user_post_url)
	                            .andReturn();
		
		return response;
	        
	}

	public static Response getUser(String username) {
        
	    Response response = 
	                	given()
		                       .contentType(ContentType.JSON)
		                       .header("api_key", "amit_password")
		                       .accept(ContentType.JSON)
		                       .pathParam("username", username)
	                    .when()
	                           .get(Routes.user_get_url)
	                           .andReturn();
		
		return response;        
	}
	
	
	public static Response updateUser(String username, User payload) {
        
	    Response response = 
	                	given()
		                       .contentType(ContentType.JSON)
		                       .header("api_key", "amit_password")
		                       .accept(ContentType.JSON)
		                       .pathParam("username", username)
		                       .body(payload)
	                    .when()
	                           .put(Routes.user_update_url)
	                           .andReturn();
		
		return response;        
	}
	
	public static Response deleteUser(String username) {
        
		System.out.println("username = "+username);
		
		    Response response = 
		                	given()
			                       .contentType(ContentType.JSON)
			                       .header("api_key", "amit_password")
			                       .accept(ContentType.JSON)
			                       .pathParam("username", username)
		                    .when()
		                           .delete(Routes.user_delete_url)
		                           .andReturn();
			
			return response;        
		}
}
