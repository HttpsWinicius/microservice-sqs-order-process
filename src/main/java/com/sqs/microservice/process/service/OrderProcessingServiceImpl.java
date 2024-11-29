package com.sqs.microservice.process.service;

import com.sqs.microservice.process.domain.OrderDomain;
import com.sqs.microservice.process.entity.OrderEntity;
import com.sqs.microservice.process.entity.OrderItemEntity;
import com.sqs.microservice.process.repository.OrderItemRepository;
import com.sqs.microservice.process.repository.OrderRepository;
import com.sqs.microservice.process.repository.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProcessingServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;



    public void processOrder(OrderDomain orderDomain) {
        try {
            LOGGER.info("Iniciando gravação no banco de dados dos pedidos...");
            OrderEntity orderEntity = OrderMapper.toOrderEntity(orderDomain);

            List<OrderItemEntity> orderItemEntities = OrderMapper.toOrderItemsEntities(orderDomain);

            orderRepository.save(orderEntity);

            orderItemRepository.saveAll(orderItemEntities);

            LOGGER.info("Gravação no banco de dados finalizada com sucesso...");

        } catch (Exception e) {
            LOGGER.error("Erro ao realizar gravação no banco de dados: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao realizar gravação no banco de dados", e);
        }
    }


}
