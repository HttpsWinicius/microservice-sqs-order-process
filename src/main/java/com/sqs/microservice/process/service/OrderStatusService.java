package com.sqs.microservice.process.service;

import com.sqs.microservice.process.domain.dto.OrderDto;

public interface OrderStatusService {

    void updateStatus(OrderDto order, String status);

}
