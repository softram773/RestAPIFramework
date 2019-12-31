package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.userDataCreationTemplate;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;

import io.restassured.response.Response;

public class CreateUserTest {
	Map<String, String> tokenAuth = new HashMap<String, String>();
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "Bearer " + "DsPMW-pp8OGygLeH5nFJjyGRwnfGoHr-3rGq";
	
	@DataProvider
	public Object[][] getUserData() {
		Object[][] userData = ExcelUtil.getTestData("userdata");
		return userData;
	}

	@Test(dataProvider = "getUserData")
	public void createUserPostCall(String firstname, String lastname, String gender, String dob, String email, String phonenumber,
			String website, String address, String status) {


		
		tokenAuth.put("Authorization", token);
		System.out.println("tokenAuth ::: " + tokenAuth);
		
		userDataCreationTemplate user = new userDataCreationTemplate(firstname, lastname, gender, dob, email, phonenumber,
				website, address, status);
		Response response = RestClient.doPOST("JSON", baseURI, basePath,
				tokenAuth, null, true, user);
		System.out.println("tokenAuth ::: " + response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println("=======================");
	}
}
