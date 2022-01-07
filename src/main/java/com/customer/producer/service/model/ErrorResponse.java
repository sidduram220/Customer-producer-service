package com.customer.producer.service.model;

public class ErrorResponse {

	private String status;
	private String message;
	private String errorType;

	public ErrorResponse(String status, String message, String errorType) {
		super();
		this.status = status;
		this.message = message;
		this.errorType = errorType;
	}

	public ErrorResponse() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
}
