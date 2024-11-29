package com.sqs.microservice.process.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
    @Id
    @Column(name="order_id")
    private String orderId;
    @Column(name="total_price")
    private double totalPrice;
    @Column(name="total_product")
    private int totalProduct;
    @Transient
    private LocalDateTime createdDate;
    @Column(name="status")
    private String status;
}
