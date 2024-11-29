package com.sqs.microservice.process.processor;

import com.sqs.microservice.process.domain.ProductDomain;
import com.sqs.microservice.process.domain.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductProcessing {

    public static ProductDomain toProductDomain(ProductDto productDto) {
        ProductDomain productDomain = new ProductDomain();
        productDomain.setNameProduct(productDto.getNameProduct());
        productDomain.setPriceProduct(productDto.getPriceProduct());
        productDomain.setAmount(productDto.getAmount());
        return productDomain;
    }


    public static List<ProductDomain> toProductDomainList(List<ProductDto> productDtos) {
        return productDtos.stream()
                .map(ProductProcessing::toProductDomain)
                .collect(Collectors.toList());
    }

}
