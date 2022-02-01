package com.customer.producer.service.service;

import com.customer.producer.service.model.Customer;

public interface CustomerService {

	public void publish(Customer customer);
}
