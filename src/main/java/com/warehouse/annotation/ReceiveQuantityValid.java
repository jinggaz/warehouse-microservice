package com.warehouse.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ReceiveQuantityValidator.class)
public @interface ReceiveQuantityValid {

	String message() default "Receive Quantity and size of products are not match!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
