package com.customer.producer.service.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.customer.producer.service.model.Address;
import com.customer.producer.service.model.Customer;
import com.customer.producer.service.model.Customer.CustomerStatusEnum;
import com.customer.producer.service.util.ObjectMapperUtil;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTests {

	@Autowired
	MockMvc mvc;

	@MockBean
	KafkaTemplate<String, Object> kafkaTemplate;

	@Test
	void publishCustomerInfoTest() throws Exception {
		Customer customer = getCustomer();
		String accessToken = obtainAccessToken();
		mvc.perform(MockMvcRequestBuilders.post("/customer/produce").header("Authorization", "bearer " + accessToken)
				.header("Transaction-Id", "101").header("Activity-Id", "102").contentType(MediaType.APPLICATION_JSON)
				.content(ObjectMapperUtil.asJsonString(customer))).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	void accessUnauthorizedTest() throws Exception {
		Customer customer = getCustomer();
		mvc.perform(MockMvcRequestBuilders.post("/customer/produce").contentType(MediaType.APPLICATION_JSON)
				.content(ObjectMapperUtil.asJsonString(customer)))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());

	}

	private String obtainAccessToken() throws Exception {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("client_id", "client");
		params.add("username", "user");
		params.add("password", "user");
		ResultActions result = mvc
				.perform(post("/oauth/token").params(params).accept("application/x-www-form-urlencoded")
						.header("authorization", "Basic Y2xpZW50OmNsaWVudHBhc3N3b3Jk"))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
		String resultString = result.andReturn().getResponse().getContentAsString();
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("access_token").toString();
	}

	private Customer getCustomer() {
		Customer c = new Customer();
		Address ad = new Address();
		ad.setAddressLine1("Chinnahothur");
		ad.setPostalCode(518395);
		c.setAddress(ad);
		c.setBirthDate(new Date());
		c.setCountry("India");
		c.setCountryCode("IN");
		c.setCustomerNumber("0123456789");
		c.setCustomerStatus(CustomerStatusEnum.O);
		c.setEmail("sidduram220@gmail.com");
		c.setFirstName("Siddaramappa");
		c.setLastName("peddahulthi");
		c.setMobileNumber("9032848303");
		return c;
	}

}
