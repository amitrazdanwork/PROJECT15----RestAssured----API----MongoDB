package com.qa.utils;

import java.time.Duration;

import io.restassured.response.Response;

public class CommonUtil {

	private static String ResponseString;

	// Method 1: For capturing the screenshot
	public static void CaptureResponse(Response response) {
		ResponseString = response.getBody().asPrettyString();
	}

	// Method 2: Get the response data as string
	public static String GetResponseString() {
		return ResponseString;
	}

	// Method 3: Pause execution
	public static void pause(Duration duration) throws InterruptedException {
		Thread.sleep(duration);
	}

}
