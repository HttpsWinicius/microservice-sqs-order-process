package com.sqs.microservice.process.domain;


import lombok.Data;

import java.util.List;


@Data
public class OrderDomain {

    private String orderId;
    private List<ProductDomain> products;
    private double totalPrice;
    private int totalProduct;
    private String status;
}
