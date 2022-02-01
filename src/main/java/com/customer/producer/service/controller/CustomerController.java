package com.customer.producer.service.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.customer.producer.service.service.CustomerService;
import com.customer.producer.service.util.ObjectMapperUtil;

@RestController
@RequestMapping("/customer/v1")
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	private static final String TRANSACTIONID = "Transaction-Id";
	private static final String ACTIVITYID = "Activity-Id";

	@Value("${success.status}")
	private String status;
	@Value("${success.message}")
	private String message;

	@Autowired
	private CustomerRequestConverter customerRequestConverter;

	@Autowired
	private CustomerService customerService;

	@PostMapping("/produce")
	public ResponseEntity<CustomerResponse> publishCustomerInfo(@Valid @RequestBody Customer customer,
			@RequestHeader(value = TRANSACTIONID) String transactionId,
			@RequestHeader(value = ACTIVITYID) String activityId) {
		String customerJsonRequest = ObjectMapperUtil
				.asJsonString(customerRequestConverter.convertRequestWithMasking(customer));
		log.info("customer request : {}", customerJsonRequest);
		customerService.publish(customer);
		CustomerResponse customerResponse = new CustomerResponse(status, message);
		String customerJsonResponse = ObjectMapperUtil.asJsonString(customerResponse);
		log.info("customer response : {}", customerJsonResponse);
		return new ResponseEntity<>(customerResponse, HttpStatus.OK);
	}
}
