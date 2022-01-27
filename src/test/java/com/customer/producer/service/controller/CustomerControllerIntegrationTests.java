package com.customer.producer.service.controller;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.customer.producer.service.model.Address;
import com.customer.producer.service.model.Customer;
import com.customer.producer.service.model.Customer.CustomerStatusEnum;
import com.customer.producer.service.util.ObjectMapperUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CustomerControllerIntegrationTests {

	@MockBean
	KafkaTemplate<String, Object> kafkaTemplate;

	@Test
	void publishCustomerInfoTest() {
		Customer customer = getCustomer();
		String jsonBody = ObjectMapperUtil.asJsonString(customer);
		String accessToken = obtainAccessToken();
		Response response = given().header("Authorization", "bearer " + accessToken).header("Transaction-Id", "101")
				.header("Activity-Id", "102").accept(ContentType.JSON).contentType(ContentType.JSON).and()
				.body(jsonBody).when().post("/customer/v1/produce");
		assertEquals(200, response.getStatusCode());
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
