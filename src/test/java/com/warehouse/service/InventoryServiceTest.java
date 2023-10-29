package com.warehouse.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
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
import com.warehouse.dto.ManufacturerDto;
import com.warehouse.dto.ProductDetailDto;
import com.warehouse.dto.ProductDto;
import com.warehouse.dto.ReceiveDto;
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
	private ReceiveDto receiveDto;
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

	@Test
	void createOrUpdateInventoryRecordWithNotNullInventoryCaseTest() {

		prepareTestData();

		final Inventory result = inventoryService.createOrUpdateInventoryRecord(inventory, receiveDto, product);

		assertThat(result.getCurrentInventory(), is(101));
		assertThat(result.getCreatedTimestamp(), not(equalTo(result.getLastUpdatedTimestamp())));
	}

	@Test
	void createOrUpdateInventoryRecordWithNullInventoryCaseTest() {

		prepareTestData();

		final Inventory result = inventoryService.createOrUpdateInventoryRecord(null, receiveDto, product);

		assertThat(result.getManufacturer().getManufacturerName(), is(product.getManufacturer().getManufacturerName()));
		assertThat(result.getCurrentInventory(), is(1));
		assertThat(result.getCreatedTimestamp(), is(result.getLastUpdatedTimestamp()));
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
		inventory.setCreatedTimestamp(Timestamp.valueOf("2023-09-01 09:01:15"));
		inventory.setLastUpdatedTimestamp(inventory.getCreatedTimestamp());

		inventories.add(inventory);

		receiveDto = new ReceiveDto();
		ManufacturerDto manufacturerDto = new ManufacturerDto();
		manufacturerDto.setManufacturerName("Apple");
		receiveDto.setManufacturer(manufacturerDto);
		ProductDto productDto = new ProductDto();
		productDto.setProductName("Macbook Pro 16");
		productDto.setDescription("Apple MacBook Pro 16 inch laptop computer");
		receiveDto.setProduct(productDto);
		receiveDto.setReceivedQuantity(1);
		ProductDetailDto productDetailDto = new ProductDetailDto();
		productDetailDto.setSerialNumber("test-serial-number");
		productDetailDto.setTagNumber("test-tag-number");
		receiveDto.setProductDetails(Collections.singletonList(productDetailDto));
	}

}
