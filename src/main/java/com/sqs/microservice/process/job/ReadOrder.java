package com.sqs.microservice.process.job;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqs.microservice.process.domain.OrderDomain;
import com.sqs.microservice.process.processor.OrderProcessing;
import com.sqs.microservice.process.util.TimeLoggerUtilProcess;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ReadOrder extends TimeLoggerUtilProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadOrder.class);

    @Autowired
    OrderProcessing orderProcessing;

    @Async
    @SqsListener("my-order-queue.fifo")
    public void readOrder (String orderQueue) {
        try {
            long startTime = System.currentTimeMillis();
            LOGGER.info("Iniciando leitura da fila SQS...");

            LOGGER.info("Mensagem recebida da fila SQS: {}", orderQueue);

            OrderDomain orderDomain = parseOrderRequest(orderQueue);

            orderProcessing.process(orderDomain);

            LOGGER.info("Tempo total para ler a fila sqs {} segundos", endTimeLogger(startTime));
            LOGGER.info("Leitura da fila sqs finalizado...");
        } catch (Exception e) {
            LOGGER.error("Erro ao processar pedido da fila SQS: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao processar pedido da fila SQS. Entre em contato com suporte.", e);
        }
    }


    private OrderDomain parseOrderRequest(String orderQueue) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(orderQueue, OrderDomain.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao processar a mensagem JSON", e);
        }
    }

}
