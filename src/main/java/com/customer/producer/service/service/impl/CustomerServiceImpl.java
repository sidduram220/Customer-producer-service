package com.customer.producer.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.customer.producer.service.model.Customer;
import com.customer.producer.service.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Value("${topic}")
	private String topic;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Override
	public void publish(Customer customer) {
		Message<Customer> message = MessageBuilder.withPayload(customer).setHeader(KafkaHeaders.TOPIC, topic).build();
		kafkaTemplate.send(message);
	}
}
