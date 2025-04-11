package com.qa.utils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.model.Test;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.qa.base.BaseTest;
import com.qa.payloads.Pet;
import com.qa.payloads.Store;
import com.qa.payloads.User;

public class DBUtil extends BaseTest {

	/* data-members */
	private static MongoClient mongoclient;
	private static MongoDatabase mongodatabase;
	private static MongoCollection<Document> mongocollection;
	private static Document mongodocument;
	// private static ConfigUtil config;

	/* Member/Action functions */
	public static void Setup_DB() {

		Map<String, String> credentials = ConfigUtil.GetDatabaseCredentials();

		Connection_Setup(credentials.get("hostname"), Integer.valueOf(credentials.get("portnumber")));

		Database_Setup(credentials.get("databasename"));
	}

	/* Action methods */
	private static void Connection_Setup(String hostname, int portnumber) {

		mongoclient = new MongoClient("localhost", 27017);

		System.out.println("Connection setup - completed");
	}

	@SuppressWarnings("deprecation")
	private static void Database_Setup(String database) {

		List<String> AllDatabases = mongoclient.getDatabaseNames();

		for (String string : AllDatabases) {

			if (string.equalsIgnoreCase(database)) {

				mongodatabase = mongoclient.getDatabase(database);

				System.out.println("Database setup - completed");

				break;
			} else {
				mongodatabase = null;

				break;
			}
		}

	}

	public static void Collection_Setup(String ModuleName) {

		System.out.println("Module Name : " + ModuleName);

		if (mongodatabase != null) {

			MongoIterable<String> CollectionNamesIterator = mongodatabase.listCollectionNames();

			MongoCursor<String> cursor = CollectionNamesIterator.cursor();

			while (cursor.hasNext()) {

				String currentValue = cursor.next();

				if (currentValue.equalsIgnoreCase(ModuleName + "_COLLECTION")) {

					mongocollection = mongodatabase.getCollection(currentValue);

					System.out.println("Collection setup - completed for module = " + currentValue);

					break;

				} else {

					System.out.println("Invalid Collection name :> " + currentValue);
				}

			}

		} else {
			System.out.println("Collection not set !!!");
			mongocollection = null;
		}

	}

	public static void Document_Setup(String data_id) {

		if (mongocollection != null) {

			int CountDocs = (int) mongocollection.countDocuments();

			if (CountDocs != 0) {

				System.out.println("Data_id = " + data_id);

				FindIterable<Document> docs = mongocollection.find(Filters.eq("data_id", data_id));

				if (!docs.first().isEmpty() && docs.first().containsKey("data_id")
						&& docs.first().containsValue(data_id)) {

					mongodocument = docs.first();
					System.out.println("doc setup - completed");

				} else {
					mongodocument = null;
					System.out.println("No valid document found ");
				}

			} else {
				System.out.println("No documents in current collection !!!");
			}

		} else {
			System.out.println("Collection not set !!!");
			mongodocument = null;
		}

	}

	public static void InitializePOJO(String moduleName) {

		if (moduleName.equalsIgnoreCase("USER")) {

			user = new User();

			if (mongodocument.containsKey("id")) {
				System.out.println(mongodocument.getInteger("id"));
				user.setId(mongodocument.getInteger("id"));
			}
			if (mongodocument.containsKey("username")) {
				System.out.println(mongodocument.getString("username"));
				user.setUsername(mongodocument.getString("username"));
			}
			if (mongodocument.containsKey("firstName")) {
				System.out.println(mongodocument.getString("firstName"));
				user.setFirstName(mongodocument.getString("firstName"));
			}
			if (mongodocument.containsKey("lastName")) {
				System.out.println(mongodocument.getString("lastName"));
				user.setLastName(mongodocument.getString("lastName"));
			}
			if (mongodocument.containsKey("email")) {
				System.out.println(mongodocument.getString("email"));
				user.setEmail(mongodocument.getString("email"));
			}
			if (mongodocument.containsKey("password")) {
				System.out.println(mongodocument.getString("password"));
				user.setPassword(mongodocument.getString("password"));
			}
			if (mongodocument.containsKey("phone")) {
				System.out.println(mongodocument.getString("phone"));
				user.setPhone(mongodocument.getString("phone"));
			}
			if (mongodocument.containsKey("userStatus")) {
				System.out.println(mongodocument.getInteger("userStatus"));
				user.setUserStatus(mongodocument.getInteger("userStatus"));
			}
			System.out.println("POJO setup - completed");

		} else if (moduleName.equalsIgnoreCase("PET")) {

		} else if (moduleName.equalsIgnoreCase("STORE")) {

		} else {
			System.out.println("Invalid Module name !!! -> " + moduleName);
		}

	}
}
