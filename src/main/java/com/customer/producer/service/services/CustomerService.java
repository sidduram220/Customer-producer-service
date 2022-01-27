package com.customer.producer.service.services;

import com.customer.producer.service.model.Customer;
import com.customer.producer.service.model.CustomerResponse;

public interface CustomerService {

	public void publish(Customer customer);

	public CustomerResponse generateCustomerResponse();

}
