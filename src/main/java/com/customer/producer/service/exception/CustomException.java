package com.customer.producer.service.exception;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.errors.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.customer.producer.service.model.ErrorResponse;

@ControllerAdvice
public class CustomException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		ErrorResponse cr = new ErrorResponse("Failure", errors.toString(), e.getClass().getSimpleName());
		return new ResponseEntity<ErrorResponse>(cr, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus("failure");
		response.setMessage(e.getMessage());
		response.setErrorType(e.getClass().getSimpleName());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorResponse> handleException(AuthenticationException e) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus("failure");
		response.setMessage(e.getMessage());
		response.setErrorType(e.getClass().getSimpleName());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

}