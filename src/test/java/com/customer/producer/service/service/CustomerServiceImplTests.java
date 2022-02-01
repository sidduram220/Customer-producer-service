package com.customer.producer.service.service;

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

@SpringBootTest
public class CustomerServiceImplTests {

	@MockBean
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	private CustomerService customerService;

	@Test
	void publishCustomerInfoTest() {
		customerService.publish(new Customer());
		verify(kafkaTemplate, times(1)).send(any(Message.class));
	}
}
