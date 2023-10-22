package com.warehouse.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.dto.InventoryDto;
import com.warehouse.dto.ReceiveDto;
import com.warehouse.service.ReceiveService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/receive")
@Validated
public class ReceiveController {

	private ReceiveService receiveService;

	public ReceiveController(ReceiveService recieveService) {
		this.receiveService = recieveService;
	}

	@PostMapping("/product")
	@Operation(summary = "Add received products into inventory")
	public ResponseEntity<List<InventoryDto>> postRecieveProduct(@RequestBody @Valid List<ReceiveDto> recieveDtos) {

		final List<InventoryDto> result = receiveService.postRecieveProduct(recieveDtos);

		return ResponseEntity.ok(result);
	}

	@GetMapping("/package/{id}")
	@Operation(summary = "Find products by recieve id")
	public ResponseEntity<ReceiveDto> getRecieveById(@PathVariable Long id) {

		final ReceiveDto result = receiveService.findById(id);

		return ResponseEntity.ok(result);
	}
}
