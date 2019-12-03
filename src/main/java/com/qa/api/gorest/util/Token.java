package com.qa.api.gorest.util;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

public class Token {

	public static Map<String, String> getAccessToken() {
	Map<String, String> farmparams = new HashMap<String, String>();
	farmparams.put("refresh_token" , "52d0f0f2fa2ff4ecbf8bc6e8d7f59813a6b6f2d5");
	farmparams.put("client_id", "3e10efb4ee6bd8a");
	farmparams.put("client_secret" , "2041bc2aa8cd5522d2468a7c9009f53af4c3f801");
	farmparams.put("grant_type" , "refresh_token");
	
	JsonPath tokenJson = given().formParams(farmparams).when().post("https://api.imgur.com/oauth2/token").then()
										.extract().jsonPath();
		return tokenJson.getMap("");
	}
	public static void main(String[] args) {
		getAccessToken();
	}
}
