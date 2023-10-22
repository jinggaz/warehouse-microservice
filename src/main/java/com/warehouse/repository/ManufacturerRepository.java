package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.entity.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

}
