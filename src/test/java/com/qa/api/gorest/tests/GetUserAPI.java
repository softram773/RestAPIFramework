package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class GetUserAPI {

	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "DsPMW-pp8OGygLeH5nFJjyGRwnfGoHr-3rGq";
	
	@Test(priority = 1)
	public void getAllUserData() {
		Response response = RestClient.doGet("JSON", baseURI, basePath, token, null, true);
		System.out.println(response.getStatusCode());
//		System.out.println(response.prettyPrint());
	}
	@Test(priority = 2)
	public void getUserData() {
		Map<String, String> paramas = new LinkedHashMap<String, String>();
		paramas.put("first_name", "Stephanie");
		paramas.put("last_name", "Brown");
		Response response = RestClient.doGet("JSON", baseURI, basePath, token, paramas, true);
		System.out.println(response.prettyPrint());
	}
}
