package com.warehouse.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.warehouse.dto.InventoryDto;
import com.warehouse.dto.ReceiveDto;
import com.warehouse.dto.mapper.MapstructMapper;
import com.warehouse.entity.Receive;
import com.warehouse.exception.ReceiveNotFoundException;
import com.warehouse.repository.ReceiveRepository;
import com.warehouse.service.help.ReceiveTransactionHelper;

@Service
public class ReceiveService {

	private static final Logger log = LoggerFactory.getLogger(ReceiveService.class);

	private ReceiveTransactionHelper receiveTransactionHelper;
	private ReceiveRepository receiveRepository;
	private MapstructMapper mapstructMapper;

	public ReceiveService(ReceiveTransactionHelper receiveTransactionHelper,
			ReceiveRepository receiveRepository, MapstructMapper mapstructMapper) {
		this.receiveTransactionHelper = receiveTransactionHelper;
		this.receiveRepository = receiveRepository;
		this.mapstructMapper = mapstructMapper;
	}

	public List<InventoryDto> postRecieveProduct(List<ReceiveDto> receiveDtos) {

		List<InventoryDto> inventoryDtos = new ArrayList<>();

		for (ReceiveDto receiveDto : receiveDtos) {

			inventoryDtos.add(receiveTransactionHelper.handleOneTransaction(receiveDto));
			
			log.info("Prodcut {} is saved into DB", receiveDto.getProduct().getProductName());
		}

		return inventoryDtos;
	}

	public ReceiveDto findById(Long id) {

		final Receive receive = receiveRepository.findByReceiveId(id)
				.orElseThrow(() -> new ReceiveNotFoundException(String.format("Receive id %d does not exist", id)));

		return mapstructMapper.receiveToReceiveDto(receive);
	}

}
