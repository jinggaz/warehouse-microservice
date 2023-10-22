package com.warehouse.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

	@JsonProperty("name")
	private String name;
	@JsonProperty("password")
	private String password;
	@JsonProperty("roles")
	private Set<RoleDto> roleDtos;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleDto> getRoleDtos() {
		return roleDtos;
	}

	public void setRoleDtos(Set<RoleDto> roleDtos) {
		this.roleDtos = roleDtos;
	}

}
