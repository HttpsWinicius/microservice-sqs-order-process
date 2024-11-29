package com.sqs.microservice.process.repository;

import com.sqs.microservice.process.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
}
