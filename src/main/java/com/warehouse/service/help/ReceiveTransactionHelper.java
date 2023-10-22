package com.warehouse.service.help;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.warehouse.dto.InventoryDto;
import com.warehouse.dto.ReceiveDto;
import com.warehouse.dto.mapper.MapstructMapper;
import com.warehouse.entity.Inventory;
import com.warehouse.entity.Product;
import com.warehouse.entity.Receive;
import com.warehouse.repository.ReceiveRepository;
import com.warehouse.service.InventoryService;
import com.warehouse.service.ProductDetailService;
import com.warehouse.service.ProductService;

@Component
public class ReceiveTransactionHelper {

	private InventoryService inventoryService;
	private ProductService productService;
	private ReceiveRepository recieveRepository;
	private ProductDetailService productDetailService;
	private MapstructMapper mapstructMapper;

	public ReceiveTransactionHelper(InventoryService inventoryService, ProductService productService,
			ReceiveRepository recieveRepository, ProductDetailService productDetailService,
			MapstructMapper mapstructMapper) {
		this.inventoryService = inventoryService;
		this.productService = productService;
		this.recieveRepository = recieveRepository;
		this.productDetailService = productDetailService;
		this.mapstructMapper = mapstructMapper;
	}

	@Transactional
	public InventoryDto handleOneTransaction(ReceiveDto recieveDto) {

		final Product product = productService.findByManufacturerIdAndProductId(
				recieveDto.getManufacturer().getManufacturerId(), recieveDto.getProduct().getProductId());

		Inventory inventory = inventoryService.findByManufacturerIdAndProductId(
				recieveDto.getManufacturer().getManufacturerId(), recieveDto.getProduct().getProductId());
		inventory = inventoryService.createOrUpdateInventoryRecord(inventory, recieveDto, product);

		final Receive receive = new Receive(product, recieveDto);
		recieveRepository.save(receive);
		inventoryService.saveInventory(inventory);

		productDetailService.saveProductionDetails(recieveDto, receive.getReceiveId());

		return mapstructMapper.inventoryToInventoryDto(inventory);
	}

}
