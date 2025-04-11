package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ConfigUtil {

	private static Logger logger = LogManager.getLogger(ConfigUtil.class);
	static Properties properties;
	FileInputStream fis;

	public ConfigUtil() {

		// Locate Config.properties file
		try {
			fis = new FileInputStream(new File(".//src//main//resources//Properties//config.properties"));
			logger.info("--Connfig.properties file got located successfully--");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.info("Problem in locating config.properties file");
			e.printStackTrace();
		}

		// Load data from Config.properties file
		properties = new Properties();

		try {
			properties.load(fis);
			logger.info("--Config properties load - success--");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("Problem in locating config.properties file");
			e.printStackTrace();
		}

	}

	public static String GetJsonSchema(String ModuleName, String ApiType) {

		JsonParser parser = new JsonParser();

		String location = null;
		String content = null;

		// GET LOCATION OF FILE

		switch (ModuleName) {
		case "USER":

			switch (ApiType) {
			case "GET":
				location = properties.getProperty("USER_GET_SCHEMA_LOCATION",
						"Property Not found - USER_GET_SCHEMA_LOCATION");
				logger.info("--Property found : USER_GET_SCHEMA_LOCATION --");
				break;
			case "POST":
				location = properties.getProperty("USER_POST_SCHEMA_LOCATION",
						"Property not found - USER_POST_SCHEMA_LOCATION");
				logger.info("--Property found : USER_POST_SCHEMA_LOCATION --");
				break;
			case "DELETE":
				location = properties.getProperty("USER_DELETE_SCHEMA_LOCATION",
						"Property not found - USER_DELETE_SCHEMA_LOCATION");
				logger.info("--Property found : USER_DELETE_SCHEMA_LOCATION --");
				break;
			case "PUT":
				location = properties.getProperty("USER_PUT_SCHEMA_LOCATION",
						"Property not found - USER_PUT_SCHEMA_LOCATION");
				logger.info("--Property found : USER_PUT_SCHEMA_LOCATION --");
				break;
			default:
				System.out.println("Invalid API type - USER MODULE");
				logger.info("--Property Not found : User Module - Invalid API type --");
				break;
			}

			break;

		case "PET":

			switch (ApiType) {
			case "GET":
				location = properties.getProperty("PET_GET_SCHEMA_LOCATION",
						"Property Not found - PET_GET_SCHEMA_LOCATION");
				logger.info("--Property found : PET_GET_SCHEMA_LOCATION --");
				break;
			case "POST":
				location = properties.getProperty("PET_POST_SCHEMA_LOCATION",
						"Property not found - PET_POST_SCHEMA_LOCATION");
				logger.info("--Property found : PET_POST_SCHEMA_LOCATION --");

				break;
			case "DELETE":
				location = properties.getProperty("PET_DELETE_SCHEMA_LOCATION",
						"Property not found - PET_DELETE_SCHEMA_LOCATION");
				logger.info("--Property found : PET_DELETE_SCHEMA_LOCATION --");
				break;
			case "PUT":
				location = properties.getProperty("PET_PUT_SCHEMA_LOCATION",
						"Property not found - PET_PUT_SCHEMA_LOCATION");
				logger.info("--Property found : PET_PUT_SCHEMA_LOCATION --");
				break;
			default:
				System.out.println("Invalid API type - PET MODULE");
				logger.info("--Property Not found : Pet Module - Invalid API type --");
				break;
			}

			break;

		case "STORE":

			switch (ApiType) {
			case "GET":
				location = properties.getProperty("STORE_GET_SCHEMA_LOCATION",
						"Property Not found - STORE_GET_SCHEMA_LOCATION");
				logger.info("--Property found : STORE_GET_SCHEMA_LOCATION --");

				break;
			case "POST":
				location = properties.getProperty("STORE_POST_SCHEMA_LOCATION",
						"Property not found - STORE_POST_SCHEMA_LOCATION");
				logger.info("--Property found : STORE_GET_SCHEMA_LOCATION --");

				break;
			case "DELETE":
				location = properties.getProperty("STORE_DELETE_SCHEMA_LOCATION",
						"Property not found - STORE_DELETE_SCHEMA_LOCATION");
				break;
			case "PUT":
				location = properties.getProperty("STORE_PUT_SCHEMA_LOCATION",
						"Property not found - STORE_PUT_SCHEMA_LOCATION");
				break;
			default:
				System.out.println("Invalid API type - STORE MODULE");
				logger.info("--Property Not found : Store Module - Invalid API type --");
				break;
			}

			break;

		default:
			System.out.println("Invalid module !!! - " + ModuleName);
			break;
		}

		// READ content from file located.
		try {

			Object o = parser.parse(new FileReader(new File(location)));
			content = String.valueOf(o);
			logger.info("--Loaded content successfully --");

		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			System.out.println("Not able to read data from json file");
			logger.info("-JsonIOException : Not able to read/write JSON --");
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			System.out.println("Not able to comprehand Json syntax");
			logger.info("-JsonSyntaxException : Not able to read malformed JSON element --");

			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Not able to locate json file");
			logger.info("--FileNotFoundException : Not able to locate Json Schema file --");
			e.printStackTrace();
		}

		// Return the read content
		return content;

	}

	public static Map<String, String> GetDatabaseCredentials() {

		Map<String, String> credentials = new HashMap<String, String>(4);

		credentials.put("hostname", properties.getProperty("database_hostname", null));
		credentials.put("portnumber", properties.getProperty("database_portnumber", null));
		credentials.put("databasename", properties.getProperty("database_name", null));
		
		return credentials;
	}

}
