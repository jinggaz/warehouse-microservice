package com.warehouse.enums;

public enum ProductStatusEnum {
	STAGING("Waiting for Tag number"), RECEIVED("Product is in the warhouse"), SHIPPED("Product is shipped");
	
	private final String value;
	
	private ProductStatusEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
