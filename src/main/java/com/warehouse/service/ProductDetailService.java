package com.warehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.warehouse.dto.ProductDetailDto;
import com.warehouse.dto.ReceiveDto;
import com.warehouse.entity.ProductDetail;
import com.warehouse.repository.ProductDetailRepository;

@Service
public class ProductDetailService {

	private ProductDetailRepository productDetailRepository;

	public ProductDetailService(ProductDetailRepository productDetailRepository) {
		this.productDetailRepository = productDetailRepository;
	}

	@Transactional
	public void saveProductionDetails(ReceiveDto recieveDto, Long receiveId) {

		for (ProductDetailDto productDtailsDto : recieveDto.getProductDetails()) {
			ProductDetail productDetail = new ProductDetail(recieveDto.getManufacturer().getManufacturerId(),
					recieveDto.getProduct().getProductId(), productDtailsDto.getSerialNumber(), receiveId);

			productDetailRepository.save(productDetail);
		}

	}

}
