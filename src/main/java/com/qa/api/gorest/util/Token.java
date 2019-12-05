package com.qa.api.gorest.util;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

public class Token {
	
	public static Map<Object, Object> appTokenMap = null;
	public static Map<String, String> authToken = new HashMap<String, String>();
	public static String clientID = "3e10efb4ee6bd8a";
	
	public static Map<Object, Object> getAccessToken() {
	Map<String, String> farmparams = new HashMap<String, String>();
	farmparams.put("refresh_token" , "52d0f0f2fa2ff4ecbf8bc6e8d7f59813a6b6f2d5");
	farmparams.put("client_id", clientID);
	farmparams.put("client_secret" , "2041bc2aa8cd5522d2468a7c9009f53af4c3f801");
	farmparams.put("grant_type" , "refresh_token");
	
	JsonPath tokenJson = given().formParams(farmparams).when().post("https://api.imgur.com/oauth2/token").then()
										.extract().jsonPath();
		appTokenMap = tokenJson.getMap("");
		return appTokenMap;
	}
	
	public static Map<String, String> getAuthToken() {
//		System.out.println(appTokenMap);
		String bearerToken = appTokenMap.get("access_token").toString();
		System.out.println("Access Token ============> " + bearerToken);
		authToken.put("Authorization", "Bearer " + bearerToken);
		return authToken;
	}
	
	public static Map<String, String> getClientID(){
		System.out.println("Client ID ============> " + clientID);
		authToken.put("Authorization", "Client-ID " + clientID);
		return authToken;
	}
}
