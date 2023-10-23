package com.warehouse.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.warehouse.annotation.ReceiveQuantityValid;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@ReceiveQuantityValid
public class ReceiveDto {

	@Valid
	@NotNull(message = "The manufacturer is required.")
	@JsonProperty("manufacturer")
	private ManufacturerDto manufacturer;

	@Valid
	@NotNull(message = "The product is required.")
	@JsonProperty("product")
	private ProductDto product;

	@NotNull(message = "The recievedQuantity is required.")
	@JsonProperty("receivedQuantity")
	private Integer receivedQuantity;

	@NotNull(message = "The recievedQuantity is required.")
	@JsonProperty("productDetails")
	private List<ProductDetailDto> productDetails;

	@JsonProperty("notes")
	private String notes;

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

	public Integer getReceivedQuantity() {
		return receivedQuantity;
	}

	public void setReceivedQuantity(Integer receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}

	public List<ProductDetailDto> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetailDto> productDetails) {
		this.productDetails = productDetails;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
