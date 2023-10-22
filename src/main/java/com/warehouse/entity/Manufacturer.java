package com.warehouse.entity;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manufacturer_id")
	private Integer manufacturerId;

	@Column(name = "manufacturer_name")
	private String manufacturerName;

	@Column(name = "created_timestamp")
	private Timestamp createdTimestamp;

	@OneToMany(mappedBy = "manufacturer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Product> products;

	public Integer getManufacturerId() {
		return manufacturerId;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String name) {
		this.manufacturerName = name;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
