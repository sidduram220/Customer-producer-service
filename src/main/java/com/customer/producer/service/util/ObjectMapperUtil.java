package com.customer.producer.service.util;

import com.customer.producer.service.exception.ObjectMapperException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {

	private ObjectMapperUtil() {
		throw new ObjectMapperException("Util class");
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException exception) {
			throw new ObjectMapperException("Exception while converting Object as json");

		}
	}

}
