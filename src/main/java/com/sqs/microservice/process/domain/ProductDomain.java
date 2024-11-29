package com.sqs.microservice.process.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductDomain {

    String nameProduct;
    double priceProduct;
    int amount;
}
