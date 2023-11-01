package com.warehouse.service;

import org.springframework.stereotype.Service;

import com.warehouse.entity.Product;
import com.warehouse.exception.ProductNotFoundException;
import com.warehouse.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product findByManufacturerIdAndProductId(int manufacturerId, int productId) {

		return productRepository.findByManufacturerIdAndProductId(productId, manufacturerId)
				.orElseThrow(() -> new ProductNotFoundException(
						String.format("Product with manufacturerId, %d, and productId, %d, was not found in Product.",
								manufacturerId, productId)));
	}

}
