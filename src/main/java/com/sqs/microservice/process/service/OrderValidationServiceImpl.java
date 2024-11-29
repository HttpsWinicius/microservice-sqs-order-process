package com.sqs.microservice.process.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderValidationServiceImpl implements OrderValidationService {
    private final Set<String> processedOrderIds = new HashSet<>();

    public boolean isDuplicate(String orderId) {
        return processedOrderIds.contains(orderId);
    }
    public void markAsProcessed(String orderId) {
        processedOrderIds.add(orderId);
    }
}
