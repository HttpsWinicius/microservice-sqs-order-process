package com.sqs.microservice.process.service;

import com.sqs.microservice.process.domain.OrderDomain;

public interface OrderProcessingService {

    void processOrder(OrderDomain orderDomain);

}
