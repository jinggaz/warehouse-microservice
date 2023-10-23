package com.warehouse.entity;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.*;

import com.warehouse.dto.ReceiveDto;

@Entity
@Table(name = "receive")
public class Receive {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receive_id")
	private Long receiveId;

	@OneToOne
	@JoinColumn(name = "manufacturer_id", nullable = false)
	private Manufacturer manufacturer;

	@OneToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(name = "received_quantity")
	private int receivedQuantity;

	@Column(name = "notes")
	private String notes;

	@Column(name = "created_timestamp")
	private Timestamp createdTimestamp;

	@Column(name = "last_updated_timestamp")
	private Timestamp lastUpdatedTimestamp;

	@OneToMany(mappedBy = "receive", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductDetail> productDetails;

	public Receive() {

	}

	public Receive(Long receiveId) {
		this.receiveId = receiveId;
	}

	public Receive(Product product, ReceiveDto recieveDto) {
		this.manufacturer = product.getManufacturer();
		this.product = product;
		this.receivedQuantity = recieveDto.getReceivedQuantity();
		this.createdTimestamp = new Timestamp(System.currentTimeMillis());
		this.lastUpdatedTimestamp = new Timestamp(System.currentTimeMillis());
	}

	public Long getReceiveId() {
		return receiveId;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getReceivedQuantity() {
		return receivedQuantity;
	}

	public void setReceivedQuantity(int receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	public Set<ProductDetail> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(Set<ProductDetail> productDetails) {
		this.productDetails = productDetails;
	}

}
