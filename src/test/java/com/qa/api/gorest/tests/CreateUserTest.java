package com.qa.api.gorest.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.userDataCreationTemplate;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;

import io.restassured.response.Response;

public class CreateUserTest {
	
	@DataProvider
	public Object[][] getUserData() {
		Object[][] userData = ExcelUtil.getTestData("userdata");
		return userData;
	}

	@Test(dataProvider = "getUserData")
	public void createUserPostCall(String firstname, String lastname, String gender, String dob, String email, String phonenumber,
			String website, String address, String status) {
//		
//		userDataCreationTemplate user = new userDataCreationTemplate("Neelesh", "Shastri", "male", "12-10-1985", "noreplay1@gmail.com", 
//											"1234567891", "https://test123.com", "Vijay bank layout Begur", "active");		
		userDataCreationTemplate user = new userDataCreationTemplate(firstname, lastname, gender, dob, email, phonenumber,
				website, address, status);
		Response response = RestClient.doPOST("JSON", "https://gorest.co.in", "/public-api/users",
											"DsPMW-pp8OGygLeH5nFJjyGRwnfGoHr-3rGq", null, true, user);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println("=======================");
	}
}
