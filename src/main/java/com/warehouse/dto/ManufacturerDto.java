package com.warehouse.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManufacturerDto {

	@JsonProperty("manufacturerId")
	private Integer manufacturerId;
	@JsonProperty("manufacturerName")
	private String manufacturerName;
	@JsonProperty("products")
	private Set<ProductDto> products;

	public Integer getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String name) {
		this.manufacturerName = name;
	}

	public Set<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductDto> products) {
		this.products = products;
	}

}
