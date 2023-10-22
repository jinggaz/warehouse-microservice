package com.warehouse.dto;

import com.warehouse.enums.RoleEnum;

public class RoleDto {

	private RoleEnum name;
	private String description;

	public RoleEnum getName() {
		return name;
	}

	public void setName(RoleEnum name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
