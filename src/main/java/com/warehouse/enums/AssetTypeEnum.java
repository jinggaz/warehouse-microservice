package com.warehouse.enums;

public enum AssetTypeEnum {
	LAPTOP("Laptop"), TABLET("Tablet"), MONITOR("Monitor"), CAMERA("Camera"), DESKTOP("Desktop");

	private final String value;

	private AssetTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
