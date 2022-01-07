package com.customer.producer.service.model;

public class CustomerResponse {

	private String status;
	private String message;

	public CustomerResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public CustomerResponse() {
		// TODO Auto-generated constructor stub
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
}