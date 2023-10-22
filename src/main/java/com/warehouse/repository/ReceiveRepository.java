package com.warehouse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.entity.Receive;

public interface ReceiveRepository extends JpaRepository<Receive, Long> {

	Optional<Receive> findByReceiveId(Long receiveId);
}
