package com.sqs.microservice.process.service;

import com.sqs.microservice.process.domain.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderCalculationServiceImpl implements OrderCalculationService {

    public double calculateTotalPrice(List<ProductDto> products) {
        return products.stream()
                .mapToDouble(product -> product.getPriceProduct() * product.getAmount())
                .sum();
    }

    public int calculateTotalProduct(List<ProductDto> products) {
        return products.stream()
                .mapToInt(ProductDto::getAmount)
                .sum();
    }

}
