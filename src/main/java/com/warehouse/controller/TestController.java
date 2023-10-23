package com.warehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping(value = "/readonly")
	@Operation(summary = "Test READ_ONLY role")
	public String readOnlyPing() {
		return "Only READ_ONLY Can Read This";
	}

	@GetMapping(value = "/readwrite")
	@Operation(summary = "Test READ_WRITE role")
	public String readWrightPing() {
		return "Only READ_WRITE Can Read This";
	}

}
