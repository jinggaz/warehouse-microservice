package com.warehouse.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.enums.AssetTypeEnum;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "manufacturer_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Manufacturer manufacturer;

	@Column(name = "asset_type")
	@Enumerated(EnumType.STRING)
	private AssetTypeEnum assetType;

	@Column(name = "is_asset")
	private Boolean isAsset;

	@Column(name = "created_timestamp")
	private Timestamp createdTimestamp;

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String name) {
		this.productName = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
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

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

}
