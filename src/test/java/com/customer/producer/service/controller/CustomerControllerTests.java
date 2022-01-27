package com.customer.producer.service.controller;

import static io.restassured.RestAssured.given;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.customer.producer.service.converter.CustomerRequestConverter;
import com.customer.producer.service.model.Address;
import com.customer.producer.service.model.Customer;
import com.customer.producer.service.model.Customer.CustomerStatusEnum;
import com.customer.producer.service.services.CustomerService;
import com.customer.producer.service.util.ObjectMapperUtil;

import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class CustomerControllerTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	KafkaTemplate<String, Object> kafkaTemplate;

	@MockBean
	CustomerService customerService;

	@MockBean
	CustomerRequestConverter customerRequestConverter;

	@Test
	void publishCustomerInfoTest() throws Exception {
		Customer customer = getCustomer();
		String jsonBody = ObjectMapperUtil.asJsonString(customer);
		String accessToken = obtainAccessToken();
		mockMvc.perform(MockMvcRequestBuilders.post("/customer/v1/produce")
				.header("Authorization", "bearer " + accessToken).header("Transaction-Id", "101")
				.header("Activity-Id", "102").contentType(MediaType.APPLICATION_JSON).content(jsonBody))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void publishCustomerInfoBadRequestTest() throws Exception {
		Customer customer = getCustomer();
		String jsonBody = ObjectMapperUtil.asJsonString(customer);
		String accessToken = obtainAccessToken();
		mockMvc.perform(
				MockMvcRequestBuilders.post("/customer/v1/produce").header("Authorization", "bearer " + accessToken)
						.contentType(MediaType.APPLICATION_JSON).content(jsonBody))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void publishCustomerInfoUnauthorizedTest() throws Exception {
		Customer customer = getCustomer();
		String jsonBody = ObjectMapperUtil.asJsonString(customer);
		mockMvc.perform(MockMvcRequestBuilders.post("/customer/v1/produce").contentType(MediaType.APPLICATION_JSON)
				.content(jsonBody)).andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	private String obtainAccessToken() {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("client_id", "client");
		params.add("username", "user");
		params.add("password", "user");
		Response response = given().params(params).accept("application/x-www-form-urlencoded")
				.header("authorization", "Basic Y2xpZW50OmNsaWVudHBhc3N3b3Jk").when().post("/oauth/token");
		String body = response.getBody().asString();
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(body).get("access_token").toString();
	}

	private Customer getCustomer() {
		Customer customer = new Customer();
		Address address = new Address();
		address.setAddressLine1("Chinnahothur");
		address.setPostalCode(518395);
		customer.setAddress(address);
		customer.setBirthDate(new Date());
		customer.setCountry("India");
		customer.setCountryCode("IN");
		customer.setCustomerNumber("0123456789");
		customer.setCustomerStatus(CustomerStatusEnum.O);
		customer.setEmail("sidduram220@gmail.com");
		customer.setFirstName("Siddaramappa");
		customer.setLastName("peddahulthi");
		customer.setMobileNumber("9032848303");
		return customer;
	}

}
