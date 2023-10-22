package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findRoleByName(String name);

}
