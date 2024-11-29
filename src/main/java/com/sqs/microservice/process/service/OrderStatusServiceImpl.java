package com.sqs.microservice.process.service;

import com.sqs.microservice.process.domain.dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    public void updateStatus(OrderDto order, String status) {
        order.setStatus(status);
    }
}
