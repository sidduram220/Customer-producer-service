package com.customer.producer.service.converter;

import com.customer.producer.service.model.Customer;
import com.customer.producer.service.model.CustomerRequest;

public interface CustomerRequestConverter {

	public CustomerRequest convertRequestWithMasking(Customer customer);
}
