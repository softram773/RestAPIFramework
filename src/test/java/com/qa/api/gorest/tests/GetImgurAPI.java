package com.qa.api.gorest.tests;

import java.util.Map;

import org.codehaus.groovy.syntax.TokenMismatchException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.Token;

import io.restassured.response.Response;

public class GetImgurAPI {
	
	Map<String, String> tokenMap;
	String accesstoken;
	String accountusername;
	String refreshtoken;
	String baseURI = "https://api.imgur.com";
	
	@BeforeMethod
	public void setUp() {
		tokenMap = Token.getAccessToken();
		accesstoken = tokenMap.get("access_token");
		accountusername = tokenMap.get("account_username");
		refreshtoken = tokenMap.get("refresh_token").toString();
	}
	
	@Test
	public void getAccountBlockStatus() {
		Response response = RestClient.doGet(null, baseURI, "/account/v1/"+accountusername+"/block", accesstoken, null, true);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	@Test
	public void getAccountBase() {
		Response response = RestClient.doGet(null, baseURI, "/3/account/"+accountusername, accesstoken, null, true);
		System.out.println(response.prettyPrint());
		System.out.println(response.getStatusCode());
	}
}
