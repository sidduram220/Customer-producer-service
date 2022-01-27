package com.customer.producer.service.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.customer.producer.service.model.ErrorResponse;

@ControllerAdvice
public class ExceptionAdvice {

	private static final String FAILURE = "Failure";

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		ErrorResponse cr = new ErrorResponse(FAILURE, errors.toString(), e.getClass().getSimpleName());
		return new ResponseEntity<>(cr, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorResponse> handleException(AuthenticationException e) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus(FAILURE);
		response.setMessage(e.getMessage());
		response.setErrorType(e.getClass().getSimpleName());
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(NoHandlerFoundException e) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus(FAILURE);
		response.setMessage(e.getMessage());
		response.setErrorType(e.getClass().getSimpleName());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<ErrorResponse> handleException(MissingRequestHeaderException e) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus(FAILURE);
		response.setMessage("Missing header : " + e.getMessage());
		response.setErrorType(e.getClass().getSimpleName());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ObjectMapperException.class)
	public ResponseEntity<ErrorResponse> handleException(ObjectMapperException e) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus(FAILURE);
		response.setMessage("Object mapper exception : " + e.getMessage());
		response.setErrorType(e.getClass().getSimpleName());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
