package com.warehouse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InventoryDto {

	@JsonProperty("inventoryId")
	private Long inventoryId;

	@JsonProperty("manufacturer")
	private ManufacturerDto manufacturer;

	@JsonProperty("product")
	private ProductDto product;

	@JsonProperty("currentInventory")
	private Integer currentInventory;

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public ManufacturerDto getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(ManufacturerDto manufacturer) {
		this.manufacturer = manufacturer;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public Integer getCurrentInventory() {
		return currentInventory;
	}

	public void setCurrentInventory(Integer currentInventory) {
		this.currentInventory = currentInventory;
	}

}
