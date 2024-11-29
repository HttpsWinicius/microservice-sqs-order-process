package com.sqs.microservice.process.service;

public interface OrderValidationService {

    boolean isDuplicate(String orderId);

    void markAsProcessed(String orderId);
}
