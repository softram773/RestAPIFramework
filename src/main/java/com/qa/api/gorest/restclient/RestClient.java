package com.qa.api.gorest.restclient;

import java.util.Map;
import org.testng.annotations.Test;

import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class RestClient {
	
	/**
	 * 
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param params
	 * @param log
	 * @return
	 */
	
	// GET call
	public static Response doGet(String contentType, String baseURI, String basePath, String token, Map<String, String> params, boolean log) {
		if(setBaseURI(baseURI)) {
		RequestSpecification request = createRequest(contentType, token, params, log);
		return executeAPI("GET", request, basePath);
		}
		return null;
	}
	
	// POST call
	public static Response doPOST(String contentType, String baseURI, String basePath, String token, 
			Map<String, String> params, boolean log, Object obj) {
				if(setBaseURI(baseURI)) {
						RequestSpecification request = createRequest(contentType, token, params, log);
						addRequestPayLoad(request, obj);
				return executeAPI("POST", request, basePath);
		}
		return null;
	}
	
	
	public static boolean setBaseURI(String baseURI) {
		if(baseURI == null || baseURI.isEmpty()) return false;
		try {
		RestAssured.baseURI = baseURI;
		return true;
		}catch(Exception e) {
			System.out.println("Invalid Base URI");
			return false;
		}
	}	
	public static void addRequestPayLoad(RequestSpecification request, Object obj) {
		String jsonPayload =  TestUtil.getSerializedJSON(obj);
		request.body(jsonPayload);
	}
	
	public static RequestSpecification createRequest(String contentType, String token, Map<String, String> params, boolean log) {
		RequestSpecification request;
		
		if(log) {
			request = RestAssured.given().log().all();
		}else {
			request = RestAssured.given();
		}
		if(token != null) {
			request.header("Authorization" , "Bearer " + token);
		}else {
			System.out.println("Please provide authentication ::: ");
		}
		if(!(params == null)) {
			request.queryParams(params);
		}
		if(contentType != null) {
		switch(contentType) {
			case "JSON" : request.contentType(ContentType.JSON);
				break;
			case "XML" : request.contentType(ContentType.XML);
				break;
			default:
				System.out.println("No Valid content type");
		}
		}
		return request;
}
	public static Response getResponse(String httpmethod, RequestSpecification request, String basePath) {
			return executeAPI(httpmethod, request, basePath);
		}
	public static Response executeAPI(String httpmethod, RequestSpecification request, String basePath) {
		Response response = null;
		switch(httpmethod) {
		case "GET":
				response = request.get(basePath);
				break;
		case "POST":
				response = request.post(basePath);
				break;
		case "PUT":
				response = request.put(basePath);
				break;
		case "DELETE":
			response = request.delete(basePath);
			break;
		default:
				System.out.println("No Valid HTTP client / method");
			break;
		}
		return response;
	}
	
}