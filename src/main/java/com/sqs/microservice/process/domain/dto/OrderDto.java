package com.sqs.microservice.process.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
public class OrderDto {

    private String orderId;
    private List<ProductDto> products;
    private String status;

}
