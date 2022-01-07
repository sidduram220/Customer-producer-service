//package com.customer.producer.service.controller;
//
//import static io.restassured.RestAssured.given;
//
//import java.util.Date;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.customer.producer.service.model.Address;
//import com.customer.producer.service.model.Customer;
//import com.customer.producer.service.model.Customer.CustomerStatusEnum;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import io.restassured.response.Response;
//
//@SpringBootTest
//public class RestAssuredTest {
//
//	@Test
//	public void postRequest() {
//		Customer c = customer();
//
//		Response response = given().header("Content-type", "application/json").body(asJsonString(c)).when()
//				.post("http://localhost:8080/customer/produce").then().extract().response();
//
//		Assertions.assertEquals(401, response.statusCode());
//	}
//
//	public static String asJsonString(final Object obj) {
//		try {
//			return new ObjectMapper().writeValueAsString(obj);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	private Customer customer() {
//		Customer c = new Customer();
//		Address ad = new Address();
//		ad.setAddressLine1("Chinnahothur");
//		ad.setPostalCode(518395);
//		c.setAddress(ad);
//		c.setBirthDate(new Date());
//		c.setCountry("India");
//		c.setCountryCode("IN");
//		c.setCustomerNumber("0123456789");
//		c.setCustomerStatus(CustomerStatusEnum.OPEN);
//		c.setEmail("sidduram220@gmail.com");
//		c.setFirstName("Siddaramappa");
//		c.setLastName("peddahulthi");
//		c.setMobileNumber(9032);
//		return c;
//	}
//
//}
