package com.sqs.microservice.process.domain;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductDomain {

    private String nameProduct;
    private double priceProduct;
    private int amount;


}
