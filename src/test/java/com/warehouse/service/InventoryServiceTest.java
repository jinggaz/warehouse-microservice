package com.warehouse.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.warehouse.dto.InventoryDto;
import com.warehouse.dto.mapper.MapstructMapper;
import com.warehouse.entity.Inventory;
import com.warehouse.entity.Manufacturer;
import com.warehouse.entity.Product;
import com.warehouse.repository.InventoryRepository;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

	@Mock
	private InventoryRepository inventoryRepository;
	@Mock
	private MapstructMapper mapstructMapper;

	@InjectMocks
	private InventoryService inventoryService;

	private Inventory inventory;
	private Product product;
	private List<Inventory> inventories;

	@Test
	void getAllInventoryHappyPathTest() {

		prepareTestData();

		when(inventoryRepository.findAll()).thenReturn(inventories);
		when(mapstructMapper.inventoryToInventoryDto(any())).thenAnswer(invocation -> {
			Inventory inventory = invocation.getArgument(0);
			InventoryDto inventoryDto = new InventoryDto();
			inventoryDto.setCurrentInventory(inventory.getCurrentInventory());
			return inventoryDto;
		});

		final List<InventoryDto> result = inventoryService.getAllInventory();

		assertThat(1, is(result.size()));
		assertThat(100, is(result.get(0).getCurrentInventory()));
	}

	private void prepareTestData() {

		inventories = new ArrayList<>();

		inventory = new Inventory();
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setManufacturerName("Apple");
		inventory.setManufacturer(manufacturer);

		product = new Product();
		product.setProductName("Macbook Pro 16");
		product.setDescription("Apple MacBook Pro 16 inch laptop computer");
		product.setManufacturer(manufacturer);
		Set<Product> products = Collections.singleton(product);
		manufacturer.setProducts(products);
		inventory.setProduct(product);
		inventory.setCurrentInventory(100);

		inventories.add(inventory);
	}

}
