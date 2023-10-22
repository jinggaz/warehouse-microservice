package com.warehouse.dto;

import com.warehouse.enums.AssetTypeEnum;

public class ProductDto {

	private Integer productId;
	private String productName;
	private Integer manufacturerId;
	private String description;
	private AssetTypeEnum assetType;
	private Boolean isAsset;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String name) {
		this.productName = name;
	}

	public Integer getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AssetTypeEnum getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetTypeEnum assetType) {
		this.assetType = assetType;
	}

	public Boolean getIsAsset() {
		return isAsset;
	}

	public void setIsAsset(Boolean isAsset) {
		this.isAsset = isAsset;
	}

}
