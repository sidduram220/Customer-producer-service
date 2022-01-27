package com.customer.producer.service.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;

import com.customer.producer.service.model.Customer;
import com.customer.producer.service.model.CustomerResponse;

@SpringBootTest
public class CustomerServiceImplTests {

	@MockBean
	KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	CustomerService customerService;

	@Test
	void publishCustomerInfoTest() {
		customerService.publish(new Customer());
		verify(kafkaTemplate, times(1)).send(any(Message.class));
	}

	@Test
	void generateCustomerResponseTest() {
		CustomerResponse customerResponse = customerService.generateCustomerResponse();
		assertEquals("Success", customerResponse.getStatus());
	}
}
