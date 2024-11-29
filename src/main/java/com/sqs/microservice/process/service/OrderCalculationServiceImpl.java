package com.sqs.microservice.process.service;

import com.sqs.microservice.process.domain.ProductDomain;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderCalculationServiceImpl {

    public double calculateTotalPrice(List<ProductDomain> products) {
        return products.stream()
                .mapToDouble(product -> product.getPriceProduct() * product.getAmount())
                .sum();
    }

    public int calculateTotalProduct(List<ProductDomain> products) {
        return products.stream()
                .mapToInt(ProductDomain::getAmount)
                .sum();
    }

}
