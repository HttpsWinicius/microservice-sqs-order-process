package com.sqs.microservice.process.repository;

import com.sqs.microservice.process.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, String> {
}
