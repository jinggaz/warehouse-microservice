package com.warehouse.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.dto.InventoryDto;
import com.warehouse.service.InventoryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	private InventoryService inventoryService;

	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@GetMapping("/all")
	@Operation(summary = "Show current inventory")
	public ResponseEntity<List<InventoryDto>> getAllInventory() {

		final List<InventoryDto> result = inventoryService.getAllInventory();

		return ResponseEntity.ok(result);
	}

}
