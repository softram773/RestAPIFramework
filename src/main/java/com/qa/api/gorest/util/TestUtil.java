package com.qa.api.gorest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
	
	public static String getSerializedJSON(Object userData) {
		ObjectMapper object = new ObjectMapper();
		String jsonPayload = null;
		try {
			jsonPayload = object.writeValueAsString(userData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonPayload;
	}

}
