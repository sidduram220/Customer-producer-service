package com.customer.producer.service.model;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Address
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-01-06T12:36:43.098662400+05:30[Asia/Calcutta]")
public class Address {
	@JsonProperty("addressLine1")
	private String addressLine1;

	@JsonProperty("addressLine2")
	private String addressLine2;

	@JsonProperty("street")
	private String street;

	@JsonProperty("postalCode")
	private Integer postalCode;

	public Address addressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
		return this;
	}

	/**
	 * Get addressLine1
	 * 
	 * @return addressLine1
	 */
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Size(max = 50)
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public Address addressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
		return this;
	}

	/**
	 * Get addressLine2
	 * 
	 * @return addressLine2
	 */
	@ApiModelProperty(value = "")

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public Address street(String street) {
		this.street = street;
		return this;
	}

	/**
	 * Get street
	 * 
	 * @return street
	 */
	@ApiModelProperty(value = "")

	@Size(max = 50)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Address postalCode(Integer postalCode) {
		this.postalCode = postalCode;
		return this;
	}

	/**
	 * Get postalCode
	 * 
	 * @return postalCode
	 */
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Address address = (Address) o;
		return Objects.equals(this.addressLine1, address.addressLine1)
				&& Objects.equals(this.addressLine2, address.addressLine2)
				&& Objects.equals(this.street, address.street) && Objects.equals(this.postalCode, address.postalCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2, street, postalCode);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Address {\n");

		sb.append("    addressLine1: ").append(toIndentedString(addressLine1)).append("\n");
		sb.append("    addressLine2: ").append(toIndentedString(addressLine2)).append("\n");
		sb.append("    street: ").append(toIndentedString(street)).append("\n");
		sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
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
