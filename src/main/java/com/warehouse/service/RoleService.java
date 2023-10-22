package com.warehouse.service;

import org.springframework.stereotype.Service;

import com.warehouse.entity.Role;
import com.warehouse.repository.RoleRepository;

@Service
public class RoleService {

	private RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Role findByName(String name) {
		return roleRepository.findRoleByName(name);
	}

}
