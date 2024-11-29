package com.sqs.microservice.process.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
public class ProductDto {

    String nameProduct;
    double priceProduct;
    int amount;
}
