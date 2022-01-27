package com.customer.producer.service.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.customer.producer.service.model.Address;
import com.customer.producer.service.model.Customer;
import com.customer.producer.service.model.Customer.CustomerStatusEnum;
import com.customer.producer.service.model.CustomerRequest;

@SpringBootTest
class CustomerRequestConverterTest {

	@Autowired
	CustomerRequestConverter customerRequestConverter;

	@Test
	void convertRequestWithMaskingTest() {
		CustomerRequest customerRequest = customerRequest();
		assertEquals("012345****", customerRequest.getCustomerNumber());
		assertEquals("****il@gmail.com", customerRequest.getEmail());

	}

	private CustomerRequest customerRequest() {
		Customer customer = new Customer();
		customer.setAddress(new Address());
		customer.setCustomerNumber("0123456789");
		customer.setEmail("mymail@gmail.com");
		customer.setBirthDate(new Date());
		customer.setCustomerStatus(CustomerStatusEnum.O);
		CustomerRequest customerRequest = customerRequestConverter.convertRequestWithMasking(customer);
		return customerRequest;
	}

}
