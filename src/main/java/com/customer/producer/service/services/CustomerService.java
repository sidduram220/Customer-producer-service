package com.customer.producer.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.customer.producer.service.model.Customer;
import com.customer.producer.service.model.CustomerResponse;

@Service
public class CustomerService {

	@Value("${topic}")
	private String topic;

	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;

	public void publish(Customer customer) {
		Message<Customer> message = MessageBuilder.withPayload(customer).setHeader(KafkaHeaders.TOPIC, topic).build();
		kafkaTemplate.send(message);
	}

	public CustomerResponse generateCustomerResponse() {
		return new CustomerResponse("Success", "message has been published");
	}

}
