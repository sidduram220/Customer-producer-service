package com.customer.producer.service.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.customer.producer.service.model.Customer;
import com.customer.producer.service.model.CustomerResponse;
import com.customer.producer.service.util.ObjectMapperUtil;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	Logger log = LoggerFactory.getLogger(CustomerController.class);

	private static final String MYTOPIC = "Mytopic1";
	private static final String TRANSACTIONID = "Transaction-Id";
	private static final String ACTIVITYID = "Activity-Id";

	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;

	@PostMapping("/produce")
	public ResponseEntity<CustomerResponse> publishCustomerInfo(@Valid @RequestBody Customer customer) {
		String jsonString = ObjectMapperUtil.asJsonString(customer);
		log.info("customer request :" + jsonString);
		Message<Customer> message = MessageBuilder.withPayload(customer).setHeader(KafkaHeaders.TOPIC, MYTOPIC)
				.setHeader(TRANSACTIONID, 101).setHeader(ACTIVITYID, 102).build();
		kafkaTemplate.send(message);
		CustomerResponse response = new CustomerResponse("Success", "message has been published");
		String jsonResponse = ObjectMapperUtil.asJsonString(response);
		log.info("customer response :" + jsonResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
