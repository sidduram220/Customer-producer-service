package com.customer.producer.service.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.customer.producer.service.exception.ObjectMapperException;
import com.customer.producer.service.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws ServletException {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus("failure");
		errorResponse.setMessage(authException.getMessage());
		errorResponse.setErrorType(authException.getClass().getSimpleName());
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getOutputStream(), errorResponse);
		} catch (IOException e) {
			throw new ObjectMapperException(e.getMessage());
		}

	}

}