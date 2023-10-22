package com.warehouse.entity;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoClassFilter;
import com.openpojo.reflection.filters.FilterEnum;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

class EntityPojoTest {

	private static final String POJO_PACKAGE = "com.warehouse.entity";

	private List<PojoClass> pojoClasses;
	private Validator validator;

	@BeforeEach
	public void setup() {
		PojoClassFilter enumFilter = new FilterEnum();
		pojoClasses = PojoClassFactory.getPojoClasses(POJO_PACKAGE, enumFilter);
		validator = ValidatorBuilder.create().with(new SetterTester()).with(new GetterTester()).build();
	}

	@Test
	void test() {
		validator.validate(pojoClasses);
	}

}
