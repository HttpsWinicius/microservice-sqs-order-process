package com.sqs.microservice.process.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
@Data
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name="order_id")
    private String orderId;

    @Transient
    private LocalDateTime createdDate;
    @Column(name="product_name")
    private String productName;
    @Column(name="price")
    private double price;
    @Column(name="quantity")
    private int quantity;

}
