package com.sqs.microservice.process.processor;

import com.sqs.microservice.process.domain.ProductDomain;
import com.sqs.microservice.process.domain.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductProcessing {

    // Método para mapear ProductDto para ProductDomain
    public static ProductDomain toProductDomain(ProductDto productDto) {
        ProductDomain productDomain = new ProductDomain();
        productDomain.setNameProduct(productDto.getNameProduct());
        productDomain.setPriceProduct(productDto.getPriceProduct());
        productDomain.setAmount(productDto.getAmount());
        return productDomain;
    }

    // Método para mapear a lista de ProductDto para uma lista de ProductDomain
    public static List<ProductDomain> toProductDomainList(List<ProductDto> productDtos) {
        return productDtos.stream()
                .map(ProductProcessing::toProductDomain)
                .collect(Collectors.toList());
    }

}
