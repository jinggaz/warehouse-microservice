package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.entity.ProductDetail;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

}
