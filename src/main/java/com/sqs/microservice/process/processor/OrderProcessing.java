package com.sqs.microservice.process.processor;

import com.sqs.microservice.process.domain.OrderDomain;
import com.sqs.microservice.process.service.OrderCalculationServiceImpl;
import com.sqs.microservice.process.service.OrderStatusServiceImpl;
import com.sqs.microservice.process.service.OrderValidationServiceImpl;
import com.sqs.microservice.process.util.TimeLoggerUtilProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessing extends TimeLoggerUtilProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProcessing.class);

    private static final String STATUS_PROCESSADO = "PROCESSADO";

    private static final String STATUS_FINALIZADO = "FINALIZADO";

    @Autowired
    private  OrderValidationServiceImpl orderValidationService;
    @Autowired
    private  OrderCalculationServiceImpl orderCalculationService;

    @Autowired
    private  OrderStatusServiceImpl orderStatusService;


    @Cacheable
    @Async
    public void process (OrderDomain orderDomain) {
        try {

            long startTime = System.currentTimeMillis();
            LOGGER.info("Iniciando processor do pedido...");

            if (orderValidationService.isDuplicate(orderDomain.getOrderId())) {
                LOGGER.info("Pedido com orderId {} já foi processado anteriormente.", orderDomain.getOrderId());
                return;
            }

            double totalPrice = orderCalculationService.calculateTotalPrice(orderDomain.getProducts());
            LOGGER.info("Valor total do pedido {}: {}", orderDomain.getOrderId(), totalPrice);

            int totalProduct = orderCalculationService.calculateTotalProduct(orderDomain.getProducts());
            LOGGER.info("Quantidade total de produto {}: {}", orderDomain.getOrderId(), totalProduct);

            orderStatusService.updateStatus(orderDomain, STATUS_PROCESSADO);

            orderStatusService.updateStatus(orderDomain, STATUS_FINALIZADO);

            orderValidationService.markAsProcessed(orderDomain.getOrderId());

            LOGGER.info("Tempo total para ler a fila SQS {} segundos", endTimeLogger(startTime));
            LOGGER.info("Leitura da fila SQS finalizada...");
        } catch (Exception e) {
            LOGGER.error("Erro ao processar pedido da fila SQS: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao processar pedido da fila SQS. Entre em contato com suporte.", e);
        }



    }

}
