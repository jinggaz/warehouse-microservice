package com.warehouse.dto.mapper;

import org.mapstruct.Mapper;

import com.warehouse.dto.InventoryDto;
import com.warehouse.dto.ManufacturerDto;
import com.warehouse.dto.ReceiveDto;
import com.warehouse.dto.UserDto;
import com.warehouse.entity.Inventory;
import com.warehouse.entity.Manufacturer;
import com.warehouse.entity.Receive;
import com.warehouse.entity.User;

@Mapper(componentModel = "spring")
public interface MapstructMapper {

	User userDtoToUser(UserDto userDto);
	
	InventoryDto inventoryToInventoryDto(Inventory inventory);

	ManufacturerDto manufacturerToManufacturerDto(Manufacturer manufacturer);
	
	ReceiveDto receiveToReceiveDto(Receive receive);
}
