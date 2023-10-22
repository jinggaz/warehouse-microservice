package com.warehouse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.warehouse.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	@Query(value = "SELECT * FROM inventory i WHERE i.product_id = ?1", nativeQuery = true)
	Inventory findbyProductId(int productId);

	@Query(value = "SELECT * FROM inventory i WHERE i.manufacturer_id = ?1 AND i.product_id = ?2", nativeQuery = true)
	Optional<Inventory> findByManufacturerIdAndProductId(Integer manufacturerId, Integer productId);
	
}
