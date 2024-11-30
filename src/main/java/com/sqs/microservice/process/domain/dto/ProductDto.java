package com.sqs.microservice.process.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    String nameProduct;
    double priceProduct;
    int amount;
}
