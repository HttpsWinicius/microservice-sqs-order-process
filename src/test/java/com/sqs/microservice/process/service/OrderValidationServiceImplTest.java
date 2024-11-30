package com.sqs.microservice.process.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OrderValidationServiceImplTest {

    private OrderValidationServiceImpl orderValidationService;

    @BeforeEach
    void setUp() {
        orderValidationService = new OrderValidationServiceImpl();
    }

    @Test
    void testIsDuplicate_ReturnsTrueOrderIdIsProcessed() {
        String orderId = "12345";


        orderValidationService.markAsProcessed(orderId);

        assertTrue(orderValidationService.isDuplicate(orderId), "O método isDuplicate deve retornar true para um orderId processado.");
    }

    @Test
    void testIsDuplicate_ReturnsFalseOrderIdIsNotProcessed() {
        String orderId = "12345";


        assertFalse(orderValidationService.isDuplicate(orderId), "O método isDuplicate deve retornar false para um orderId não processado.");
    }

    @Test
    void testMarkAsProcessed() {
        String orderId = "12345";


        orderValidationService.markAsProcessed(orderId);


        assertTrue(orderValidationService.isDuplicate(orderId), "O orderId deve ser considerado duplicado após ser marcado como processado.");
    }

    @Test
    void testMarkAsProcessed_DuplicateOrderIds() {
        String orderId1 = "12345";
        String orderId2 = "67890";

        orderValidationService.markAsProcessed(orderId1);
        orderValidationService.markAsProcessed(orderId2);

        assertTrue(orderValidationService.isDuplicate(orderId1), "O orderId1 deveria ser considerado duplicado.");
        assertTrue(orderValidationService.isDuplicate(orderId2), "O orderId2 deveria ser considerado duplicado.");
    }
}
