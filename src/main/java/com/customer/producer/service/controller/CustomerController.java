package com.customer.producer.service.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.producer.service.converter.CustomerRequestConverter;
import com.customer.producer.service.model.Customer;
import com.customer.producer.service.model.CustomerResponse;
import com.customer.producer.service.services.CustomerService;
import com.customer.producer.service.util.ObjectMapperUtil;

@RestController
@RequestMapping("/customer/v1")
public class CustomerController {

	Logger log = LoggerFactory.getLogger(CustomerController.class);

	private static final String TRANSACTIONID = "Transaction-Id";
	private static final String ACTIVITYID = "Activity-Id";

	@Autowired
	CustomerRequestConverter customerRequestConverter;

	@Autowired
	CustomerService customerService;

	@PostMapping("/produce")
	public ResponseEntity<CustomerResponse> publishCustomerInfo(@Valid @RequestBody Customer customer,
			@RequestHeader(value = TRANSACTIONID) String transactionId,
			@RequestHeader(value = ACTIVITYID) String activityId) {
		String customerJsonRequest = ObjectMapperUtil
				.asJsonString(customerRequestConverter.convertRequestWithMasking(customer));
		log.info("customer request : {}", customerJsonRequest);
		customerService.publish(customer);
		CustomerResponse customerResponse = customerService.generateCustomerResponse();
		String customerJsonResponse = ObjectMapperUtil.asJsonString(customerResponse);
		log.info("customer response : {}", customerJsonResponse);
		return new ResponseEntity<>(customerResponse, HttpStatus.OK);

	}

}
