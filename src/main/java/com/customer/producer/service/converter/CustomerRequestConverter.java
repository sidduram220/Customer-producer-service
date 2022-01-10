package com.customer.producer.service.converter;

import org.springframework.stereotype.Service;

import com.customer.producer.service.model.Customer;
import com.customer.producer.service.model.CustomerRequest;

@Service
public class CustomerRequestConverter {

	public CustomerRequest covertRequestWithMasking(Customer customer) {
		CustomerRequest cr = new CustomerRequest();
		cr.setAddress(customer.getAddress());
		cr.setBirthDate(maskString(customer.getBirthDate().toString(), 0, 4, '*'));
		cr.setCountry(customer.getCountry());
		cr.setCountryCode(customer.getCountryCode());
		cr.setCustomerNumber(maskString(customer.getCustomerNumber(), 6, 10, '*'));
		cr.setCustomerStatus(customer.getCustomerStatus().name());
		cr.setEmail(maskString(customer.getEmail(), 0, 4, '*'));
		cr.setFirstName(customer.getFirstName());
		cr.setLastName(customer.getLastName());
		cr.setMobileNumber(customer.getMobileNumber());
		return cr;
	}

	private static String maskString(String strText, int start, int end, char maskChar) {
		int maskLength = end - start;
		StringBuilder sbMaskString = new StringBuilder(maskLength);
		for (int i = 0; i < maskLength; i++) {
			sbMaskString.append(maskChar);
		}
		return strText.substring(0, start) + sbMaskString.toString() + strText.substring(start + maskLength);
	}

}
