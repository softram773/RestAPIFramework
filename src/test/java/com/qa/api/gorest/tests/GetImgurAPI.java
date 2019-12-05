package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.groovy.syntax.TokenMismatchException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.Token;

import io.restassured.response.Response;

public class GetImgurAPI {
	
	Map<Object, Object> tokenMap;
	Map<String, String> authTokenMap;
	String accesstoken;
	String accountusername;
	String refreshtoken;
	String baseURI = "https://api.imgur.com";
	
	@BeforeMethod
	public void setUp() {
		tokenMap = Token.getAccessToken();
		accesstoken = tokenMap.get("access_token").toString();
		accountusername = tokenMap.get("account_username").toString();
		refreshtoken = tokenMap.get("refresh_token").toString();
	}
	
	@Test
	public void getAccountBlockStatus() {
		authTokenMap = Token.getAuthToken();
		Response response = RestClient.doGet(null, baseURI, "/account/v1/"+accountusername+"/block", authTokenMap, null, true);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	@Test
	public void getAccountBase() {
		authTokenMap = Token.getAuthToken();
		Response response = RestClient.doGet(null, baseURI, "/3/account/"+accountusername, authTokenMap, null, true);
		System.out.println(response.prettyPrint());
		System.out.println(response.getStatusCode());
	}
	@Test
	public void uploadImagePostAPITest() {
		Map<String, String> clientIDMap = Token.getClientID();
		Map<String, String> formMap = new HashMap<String, String>();
		formMap.put("title","Image title");
		formMap.put("description", "Image Description");
		Response response = RestClient.doPOST("multipart", "https://api.imgur.com", "/3/upload", clientIDMap, null, true, formMap);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
}
