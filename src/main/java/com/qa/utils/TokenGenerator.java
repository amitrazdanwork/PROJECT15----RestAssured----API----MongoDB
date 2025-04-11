package com.qa.utils;

import java.time.Instant;

import io.restassured.RestAssured;
import io.restassured.response.Response;


/* This function is required to handle token expiration during execution of test scripts */
//For this framework, this is not required as we do not have any Token involved for validation */
// This is mainly involved when we have token with expiry of 1min/1hr and our test execution is going to take more time than that.


public class TokenGenerator {
	

	private static String accessToken;
	private static Instant tokenExpiryTime;
	static Response response;
	static int expiresIn;

	// Method to obtain initial token
	public static String getAccessToken() {
		if (accessToken == null || isTokenExpired()) {
			refreshAccessToken();
		}
		return accessToken;

	}

	// Method to check if the token is expired
	private static boolean isTokenExpired() {
		return tokenExpiryTime == null || Instant.now().isAfter(tokenExpiryTime);
	}

	// Method to refresh token
	private static void refreshAccessToken() {
	response = RestAssured.given()
			.contentType("application/x-www-form-urlencoded")
			.formParam("grant_type", "client_credentials")
			.formParam("client_id", "your-client-id")
			.formParam("client_secret", "your-client-secret")
			.post("https://your-auth-server.com/oauth/token");
	 
	accessToken = response.jsonPath().getString("access_token");
	expiresIn = response.jsonPath().getInt("expires_in");
	tokenExpiryTime = Instant.now().plusSeconds(expiresIn);
	
	System.out.println("Access Token: " + accessToken);
	System.out.println("Token Expiry Time: " + tokenExpiryTime);
	    }
}
