package com.warehouse.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehouse.enums.ProductStatusEnum;

@Entity
@Table(name = "product_detail")
public class ProductDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_detail_id")
	private Long productDetailId;

	@Column(name = "manufacturer_id")
	private int manufacturerId;

	@Column(name = "product_id")
	private int productId;

	@Column(name = "serial_number")
	private String serialNumber;

	@Column(name = "tag_number")
	private String tagNumber;

	@Column(name = "product_status")
	@Enumerated(EnumType.STRING)
	private ProductStatusEnum productStatus;

	@Column(name = "created_timestamp")
	private Timestamp createdTimestamp;

	@Column(name = "last_updated_timestamp")
	private Timestamp lastUpdatedTimestamp;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "receive_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Receive receive;

	public ProductDetail() {

	}

	public ProductDetail(int manufacturerId, int productId, String serialNumber, long receiveId) {
		this.receive = new Receive(receiveId);
		this.manufacturerId = manufacturerId;
		this.productId = productId;
		this.serialNumber = serialNumber;
		this.productStatus = ProductStatusEnum.STAGING;
		this.createdTimestamp = new Timestamp(System.currentTimeMillis());
		this.lastUpdatedTimestamp = new Timestamp(System.currentTimeMillis());
	}

	public Long getProductDetailId() {
		return productDetailId;
	}

	public int getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getTagNumber() {
		return tagNumber;
	}

	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
	}

	public ProductStatusEnum getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(ProductStatusEnum productStatus) {
		this.productStatus = productStatus;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Timestamp getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	public void setLastUpdatedTimestamp(Timestamp lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}

	public void setProductDetailId(Long productDetailId) {
		this.productDetailId = productDetailId;
	}

}
