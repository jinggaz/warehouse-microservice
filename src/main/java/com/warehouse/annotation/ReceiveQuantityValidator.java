package com.warehouse.annotation;

import com.warehouse.dto.ReceiveDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ReceiveQuantityValidator implements ConstraintValidator<ReceiveQuantityValid, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if (!(value instanceof ReceiveDto)) {
			throw new IllegalArgumentException("@ReceiveQuantityValid only applies to ReceiveDto Object");
		}

		ReceiveDto receivedto = (ReceiveDto) value;

		return receivedto.getRecievedQuantity() == receivedto.getProductDetails().size();
	}

}
