package com.sqs.microservice.process.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class OrderDomain {

    String orderId;
    List<ProductDomain> products;
    String status;

}
