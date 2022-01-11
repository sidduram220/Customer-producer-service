package com.customer.producer.service.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.errors.AuthenticationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.customer.producer.service.model.ErrorResponse;

@ControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {

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

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorResponse> handleException(AuthenticationException e) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus("failure");
		response.setMessage(e.getMessage());
		response.setErrorType(e.getClass().getSimpleName());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadRequest.class)
	public ResponseEntity<ErrorResponse> handleException(BadRequest e) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus("failure");
		response.setMessage(e.getMessage());
		response.setErrorType(e.getClass().getSimpleName());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(NoHandlerFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ResponseEntity<ErrorResponse> handleException(NoHandlerFoundException e) {
//		ErrorResponse response = new ErrorResponse();
//		response.setStatus("failure");
//		response.setMessage(e.getMessage());
//		response.setErrorType(e.getClass().getSimpleName());
//		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
//	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus("failure");
		response.setMessage(ex.getMessage());
		response.setErrorType(ex.getClass().getSimpleName());
		// TODO Auto-generated method stub
		return super.handleNoHandlerFoundException(ex, headers, status, request);
	}

}
