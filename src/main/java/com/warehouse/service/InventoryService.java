package com.warehouse.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.warehouse.dto.InventoryDto;
import com.warehouse.dto.ReceiveDto;
import com.warehouse.dto.mapper.MapstructMapper;
import com.warehouse.entity.Inventory;
import com.warehouse.entity.Product;
import com.warehouse.exception.ProductNotFoundException;
import com.warehouse.repository.InventoryRepository;

@Service
public class InventoryService {

	private InventoryRepository inventoryRepository;
	private MapstructMapper mapstructMapper;

	public InventoryService(InventoryRepository inventoryRepository, MapstructMapper mapstructMapper) {
		this.inventoryRepository = inventoryRepository;
		this.mapstructMapper = mapstructMapper;
	}

	public List<InventoryDto> getAllInventory() {

		final List<Inventory> result = inventoryRepository.findAll();

		return result.stream().map(it -> mapstructMapper.inventoryToInventoryDto(it)).toList();
	}

	public Inventory createOrUpdateInventoryRecord(Inventory inventory, ReceiveDto receiveDto, Product product) {

		if (null != inventory) {
			inventory.setCurrentInventory(inventory.getCurrentInventory() + receiveDto.getReceivedQuantity());
			inventory.setLastUpdatedTimestamp(new Timestamp(System.currentTimeMillis()));
		} else {
			inventory = new Inventory();
			inventory.setManufacturer(product.getManufacturer());
			inventory.setProduct(product);
			inventory.setCurrentInventory(receiveDto.getReceivedQuantity());
		}

		return inventory;
	}

	public Inventory findByManufacturerIdAndProductId(Integer manufacturerId, Integer productId) {

		return inventoryRepository.findByManufacturerIdAndProductId(manufacturerId, productId)
				.orElseThrow(() -> new ProductNotFoundException(
						String.format("ManufacturerId, %d, with productId, %d, was not found in Inventory.",
								manufacturerId, productId)));
	}

	public Inventory saveInventory(Inventory inventory) {

		return inventoryRepository.save(inventory);
	}

}
