package com.sqs.microservice.process.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqs.microservice.process.domain.OrderDomain;
import com.sqs.microservice.process.domain.ProductDomain;
import com.sqs.microservice.process.domain.dto.OrderDto;
import com.sqs.microservice.process.service.*;
import com.sqs.microservice.process.util.TimeLoggerUtilProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProcessing extends TimeLoggerUtilProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProcessing.class);

    private static final String STATUS_FINALIZADO = "FINALIZADO";

    @Autowired
    private OrderValidationService orderValidationService;
    @Autowired
    private OrderCalculationService orderCalculationService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private OrderProcessingService orderProcessingService;

    @Cacheable
    @Async
    public void process (String orderQueue) {
        try {

            long startTime = System.currentTimeMillis();
            LOGGER.info("Iniciando process do pedido...");

            OrderDto orderDto = parseOrderRequest(orderQueue);

            if (orderValidationService.isDuplicate(orderDto.getOrderId())) {
                LOGGER.info("Pedido com orderId {} já foi processado anteriormente.", orderDto.getOrderId());
                return;
            }

            double totalPrice = orderCalculationService.calculateTotalPrice(orderDto.getProducts());

            int totalProduct = orderCalculationService.calculateTotalProduct(orderDto.getProducts());

            OrderDomain orderDomain = getValues(orderDto, totalPrice, totalProduct);

            orderValidationService.markAsProcessed(orderDomain.getOrderId());

            orderProcessingService.processOrder(orderDomain);

            LOGGER.info("Tempo total para ler a fila SQS {} segundos", endTimeLogger(startTime));
            LOGGER.info("Leitura da fila SQS finalizada...");
        } catch (Exception e) {
            LOGGER.error("Erro ao processar pedido da fila SQS: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao processar pedido da fila SQS. Entre em contato com suporte.", e);
        }

    }


    private OrderDomain getValues(OrderDto orderDto, double totalPrice, int totalProduct) {
        LOGGER.info("Realizando mapper para domain...");

        OrderDomain orderDomain = new OrderDomain();
        orderDomain.setOrderId(orderDto.getOrderId());
        orderDomain.setTotalPrice(totalPrice);
        orderDomain.setTotalProduct(totalProduct);
        orderDomain.setStatus(STATUS_FINALIZADO);

        List<ProductDomain> productDomains = ProductProcessing.toProductDomainList(orderDto.getProducts());
        orderDomain.setProducts(productDomains);

        LOGGER.info("Finalizado com sucesso mapper para domain...");

        return orderDomain;

    }

    private OrderDto parseOrderRequest(String orderQueue) {
        LOGGER.info("Realizando parse do pedido recebido em Json...");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(orderQueue, OrderDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao processar a mensagem JSON", e);
        }
    }

}
