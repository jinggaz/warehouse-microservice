package com.warehouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.warehouse.dto.ManufacturerDto;
import com.warehouse.dto.mapper.MapstructMapper;
import com.warehouse.entity.Manufacturer;
import com.warehouse.repository.ManufacturerRepository;

@Service
public class ManufacturerService {

	private ManufacturerRepository manufacturerRepository;
	private MapstructMapper mapstructMapper;

	public ManufacturerService(ManufacturerRepository manufacturerRepository, MapstructMapper mapstructMapper) {
		this.manufacturerRepository = manufacturerRepository;
		this.mapstructMapper = mapstructMapper;
	}

	public List<ManufacturerDto> getAllManufacturer() {

		final List<Manufacturer> result = manufacturerRepository.findAll();

		return result.stream().map(it -> mapstructMapper.manufacturerToManufacturerDto(it)).toList();
	}

}
