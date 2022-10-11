package com.org.promotion.configs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "price" })
public class ProductProperties implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("price")
	private Double price;

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}
}
