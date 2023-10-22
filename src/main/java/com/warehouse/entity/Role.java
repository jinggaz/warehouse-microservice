package com.warehouse.entity;

import java.sql.Timestamp;

import com.warehouse.enums.RoleEnum;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "name")
	@Enumerated(EnumType.STRING)
	private RoleEnum name;

	@Column(name = "description")
	private String description;

	@Column(name = "created")
	private Timestamp created;

	public Integer getRoleId() {
		return roleId;
	}

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

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

}
