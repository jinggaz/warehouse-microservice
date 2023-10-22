package com.warehouse.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.warehouse.dto.InventoryDto;
import com.warehouse.service.InventoryService;

@ExtendWith(MockitoExtension.class)
class InventoryControllerTest {

	@Mock
	private InventoryService inventoryService;

	@InjectMocks
	private InventoryController controller;

	private MockMvc mockMvc;

	private List<InventoryDto> result;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		result = new ArrayList<>();
	}

	@Test
	void getAllInventoryHappyPath() throws Exception {

		when(inventoryService.getAllInventory()).thenReturn(result);

		mockMvc.perform(get("/inventory/all").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
	}

}
