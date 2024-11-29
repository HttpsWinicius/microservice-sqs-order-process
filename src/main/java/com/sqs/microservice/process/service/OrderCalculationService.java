package com.sqs.microservice.process.service;

import com.sqs.microservice.process.domain.dto.ProductDto;

import java.util.List;

public interface OrderCalculationService {

    double calculateTotalPrice(List<ProductDto> products);

    int calculateTotalProduct(List<ProductDto> products);
}
