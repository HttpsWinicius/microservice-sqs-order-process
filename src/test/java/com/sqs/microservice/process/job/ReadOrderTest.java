package com.sqs.microservice.process.job;

import com.sqs.microservice.process.processor.OrderProcessing;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.concurrent.CountDownLatch;

import static org.mockito.Mockito.*;

@SpringBootTest
class ReadOrderTest {

    private static final String ORDER_QUEUE_JSON = "{\"orderId\": \"123\", \"products\": [{\"nameProduct\": \"Cerveja A\", \"priceProduct\": 15, \"amount\": 2}]}";

    @InjectMocks
    private ReadOrder readOrder;

    @Mock
    private OrderProcessing orderProcessing;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadOrderSuccess() {
        String orderQueue = ORDER_QUEUE_JSON;

        doNothing().when(orderProcessing).process(anyString());

        readOrder.readOrder(orderQueue);

        verify(orderProcessing, times(1)).process(orderQueue);
    }

    @Test
    void testReadOrderSuccessWithAsync() throws InterruptedException {
        String orderQueue = ORDER_QUEUE_JSON;

        doNothing().when(orderProcessing).process(anyString());

        CountDownLatch latch = new CountDownLatch(1);
        doAnswer(invocation -> {
            latch.countDown();
            return null;
        }).when(orderProcessing).process(anyString());

        readOrder.readOrder(orderQueue);
        latch.await();

        verify(orderProcessing, times(1)).process(orderQueue);
    }



}
