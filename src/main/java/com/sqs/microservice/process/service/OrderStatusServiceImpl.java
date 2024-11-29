package com.sqs.microservice.process.service;

import com.sqs.microservice.process.domain.OrderDomain;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusServiceImpl {

    public void updateStatus(OrderDomain order, String status) {
        order.setStatus(status);
    }
}
