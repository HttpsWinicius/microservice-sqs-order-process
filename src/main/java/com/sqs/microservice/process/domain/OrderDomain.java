package com.sqs.microservice.process.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class OrderDomain {

    private String orderId;
    private List<ProductDomain> products;
    private double totalPrice;
    private int totalProduct;
    private String status;
}
