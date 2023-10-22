package com.warehouse.enums;

public enum RoleEnum {
	READ_ONLY("Read only role"), READ_WRITE("Read and wright role");

	private final String value;

	private RoleEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
