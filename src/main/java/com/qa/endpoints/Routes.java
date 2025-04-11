package com.qa.endpoints;

public class Routes {

	private static String base_url = "https://petstore.swagger.io/v2";

	// USER module URLs
	protected static String user_post_url = base_url + "/user";
	protected static String user_get_url = base_url + "/user/{username}";
	protected static String user_update_url = base_url + "/user/{username}";
	protected static String user_delete_url = base_url + "/user/{username}";

	// PET module URLs
	protected static String pet_post_url = base_url + "/pet";
	protected static String pet_get_url = base_url + "/pet/{pid}";
	protected static String pet_update_url = base_url + "/pet/{pid}";
	protected static String pet_delete_url = base_url + "/pet/{pid}";
	
	// STORE module URLs
	protected static String store_post_url = base_url + "/store";
	protected static String store_get_url = base_url + "/store/{username}";
	protected static String store_update_url = base_url + "/store/{username}";
	protected static String store_delete_url = base_url + "/store/{username}";
}
