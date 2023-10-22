package com.warehouse.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.dto.ManufacturerDto;
import com.warehouse.service.ManufacturerService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

	private ManufacturerService manufacturerService;

	public ManufacturerController(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}

	@GetMapping("/all")
	@Operation(summary = "Show current manufacturers")
	public ResponseEntity<List<ManufacturerDto>> getAllManufacturer() {

		final List<ManufacturerDto> result = manufacturerService.getAllManufacturer();

		return ResponseEntity.ok(result);
	}

}
