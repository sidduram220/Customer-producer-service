package com.customer.producer.service.model;

import java.util.Date;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * Customer
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-01-06T12:36:43.098662400+05:30[Asia/Calcutta]")
public class Customer {
	@JsonProperty("customerNumber")
	private String customerNumber;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("birthDate")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date birthDate;

	@JsonProperty("country")
	private String country;

	@JsonProperty("countryCode")
	private String countryCode;

	@JsonProperty("mobileNumber")
	private Integer mobileNumber;

	@JsonProperty("email")
	private String email;

	/**
	 * customer status
	 */
	public enum CustomerStatusEnum {
		OPEN("Open"),

		CLOSE("Close"),

		SUSPENDED("Suspended"),

		RESTORED("Restored");

		private String value;

		CustomerStatusEnum(String value) {
			this.value = value;
		}

		@JsonValue
		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static CustomerStatusEnum fromValue(String value) {
			for (CustomerStatusEnum b : CustomerStatusEnum.values()) {
				if (b.value.equals(value)) {
					return b;
				}
			}
			throw new IllegalArgumentException("Unexpected value '" + value + "'");
		}
	}

	@JsonProperty("customerStatus")
	private CustomerStatusEnum customerStatus;

	@JsonProperty("address")
	private Address address;

	public Customer customerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
		return this;
	}

	/**
	 * Get customerNumber
	 * 
	 * @return customerNumber
	 */
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Size(max = 10)
	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Customer firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	/**
	 * Get firstName
	 * 
	 * @return firstName
	 */
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Size(min = 10, max = 50)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Customer lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	/**
	 * Get lastName
	 * 
	 * @return lastName
	 */
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Size(min = 10, max = 50)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Customer birthDate(Date birthDate) {
		this.birthDate = birthDate;
		return this;
	}

	/**
	 * Get birthDate
	 * 
	 * @return birthDate
	 */
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Customer country(String country) {
		this.country = country;
		return this;
	}

	/**
	 * Get country
	 * 
	 * @return country
	 */
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Customer countryCode(String countryCode) {
		this.countryCode = countryCode;
		return this;
	}

	/**
	 * Get countryCode
	 * 
	 * @return countryCode
	 */
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Size(max = 2)
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Customer mobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
		return this;
	}

	/**
	 * Get mobileNumber
	 * 
	 * @return mobileNumber
	 */
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public Integer getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Customer email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * Get email
	 * 
	 * @return email
	 */
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Size(max = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer customerStatus(CustomerStatusEnum customerStatus) {
		this.customerStatus = customerStatus;
		return this;
	}

	/**
	 * customer status
	 * 
	 * @return customerStatus
	 */
	@ApiModelProperty(required = true, value = "customer status")
	@NotNull

	public CustomerStatusEnum getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(CustomerStatusEnum customerStatus) {
		this.customerStatus = customerStatus;
	}

	public Customer address(Address address) {
		this.address = address;
		return this;
	}

	/**
	 * Get address
	 * 
	 * @return address
	 */
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Customer customer = (Customer) o;
		return Objects.equals(this.customerNumber, customer.customerNumber)
				&& Objects.equals(this.firstName, customer.firstName)
				&& Objects.equals(this.lastName, customer.lastName)
				&& Objects.equals(this.birthDate, customer.birthDate) && Objects.equals(this.country, customer.country)
				&& Objects.equals(this.countryCode, customer.countryCode)
				&& Objects.equals(this.mobileNumber, customer.mobileNumber)
				&& Objects.equals(this.email, customer.email)
				&& Objects.equals(this.customerStatus, customer.customerStatus)
				&& Objects.equals(this.address, customer.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerNumber, firstName, lastName, birthDate, country, countryCode, mobileNumber, email,
				customerStatus, address);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Customer {\n");

		sb.append("    customerNumber: ").append(toIndentedString(customerNumber)).append("\n");
		sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
		sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
		sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
		sb.append("    country: ").append(toIndentedString(country)).append("\n");
		sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
		sb.append("    mobileNumber: ").append(toIndentedString(mobileNumber)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("    customerStatus: ").append(toIndentedString(customerStatus)).append("\n");
		sb.append("    address: ").append(toIndentedString(address)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
