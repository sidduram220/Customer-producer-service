package com.customer.producer.service.converter;

import org.springframework.stereotype.Service;

import com.customer.producer.service.model.Customer;
import com.customer.producer.service.model.CustomerRequest;

@Service
public class CustomerRequestConverter {

	private static final String firstFourDigits = "(?<!....).";
	private static final String lastFourDigits = "\\w(?!\\w{4})";

	public CustomerRequest covertRequestWithMasking(Customer customer) {
		CustomerRequest customerRequest = new CustomerRequest();
		customerRequest.setAddress(customer.getAddress());
		customerRequest.setBirthDate(customer.getBirthDate().toString().replaceAll(firstFourDigits, "*"));
		customerRequest.setCountry(customer.getCountry());
		customerRequest.setCountryCode(customer.getCountryCode());
		customerRequest.setCustomerNumber(customer.getCustomerNumber().replaceAll(lastFourDigits, "*"));
		customerRequest.setCustomerStatus(customer.getCustomerStatus().name());
		customerRequest.setEmail(customer.getEmail().replaceAll(firstFourDigits, "*"));
		customerRequest.setFirstName(customer.getFirstName());
		customerRequest.setLastName(customer.getLastName());
		customerRequest.setMobileNumber(customer.getMobileNumber());
		return customerRequest;
	}
}
