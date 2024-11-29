package com.sqs.microservice.process.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDomain {

    private String nameProduct;
    private double priceProduct;
    private int amount;
}
