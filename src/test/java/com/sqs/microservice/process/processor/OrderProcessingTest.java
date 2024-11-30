package com.sqs.microservice.process.processor;

import com.sqs.microservice.process.domain.dto.OrderDto;
import com.sqs.microservice.process.service.OrderCalculationService;
import com.sqs.microservice.process.service.OrderProcessingService;
import com.sqs.microservice.process.service.OrderValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
class OrderProcessingTest {

    @InjectMocks
    private OrderProcessing orderProcessing;

    @Mock
    private OrderValidationService orderValidationService;

    @Mock
    private OrderCalculationService orderCalculationService;

    @Mock
    private OrderProcessingService orderProcessingService;

    @Mock
    private OrderDto orderDto;

    private static final String ORDER_QUEUE_JSON = "{\"orderId\": 123, \"products\": []}";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessOrder_whenOrderIsNotDuplicate_shouldProcessSuccessfully() {

        when(orderValidationService.isDuplicate(any())).thenReturn(false);
        when(orderCalculationService.calculateTotalPrice(any())).thenReturn(100.0);
        when(orderCalculationService.calculateTotalProduct(any())).thenReturn(5);


        orderProcessing.process(ORDER_QUEUE_JSON);


        verify(orderValidationService, times(1)).isDuplicate(any());
        verify(orderCalculationService, times(1)).calculateTotalPrice(any());
        verify(orderCalculationService, times(1)).calculateTotalProduct(any());
        verify(orderProcessingService, times(1)).processOrder(any());
    }

    @Test
    public void testProcessOrder_whenOrderIsDuplicate_shouldNotProcess() {

        when(orderValidationService.isDuplicate(any())).thenReturn(true);


        orderProcessing.process(ORDER_QUEUE_JSON);


        verify(orderValidationService, times(1)).isDuplicate(any());
        verify(orderCalculationService, never()).calculateTotalPrice(any());
        verify(orderProcessingService, never()).processOrder(any());
    }


    @Test
    public void testProcessOrder_whenExceptionOccurs_shouldHandleGracefully() {

        when(orderValidationService.isDuplicate(any())).thenReturn(false);
        when(orderCalculationService.calculateTotalPrice(any())).thenThrow(new RuntimeException("Calculando preço falhou"));

        try {
            orderProcessing.process(ORDER_QUEUE_JSON);
        } catch (RuntimeException e) {
            assert(e.getMessage().contains("Falha ao processar pedido da fila SQS"));
        }
    }


}
