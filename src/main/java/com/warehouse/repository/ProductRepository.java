package com.warehouse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.warehouse.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT * FROM product p WHERE p.product_id = ?1 AND p.manufacturer_id = ?2",nativeQuery = true)
	Optional<Product> findByManufacturerIdAndProductId(int productId, int manufacturerId);

}
