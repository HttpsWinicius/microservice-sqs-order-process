package com.sqs.microservice.process.repository.mapper;

import com.sqs.microservice.process.domain.OrderDomain;
import com.sqs.microservice.process.domain.ProductDomain;
import com.sqs.microservice.process.entity.OrderEntity;
import com.sqs.microservice.process.entity.OrderItemEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {


    public static OrderEntity toOrderEntity(OrderDomain orderDomain) {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderDomain.getOrderId());
        orderEntity.setTotalPrice(orderDomain.getTotalPrice());
        orderEntity.setTotalProduct(orderDomain.getTotalProduct());
        orderEntity.setStatus(orderDomain.getStatus());

        return orderEntity;
    }

    public static List<OrderItemEntity> toOrderItemsEntities(OrderDomain orderDomain) {

        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        for (ProductDomain product : orderDomain.getProducts()) {
            OrderItemEntity item = new OrderItemEntity();
            item.setProductName(product.getNameProduct());
            item.setPrice(product.getPriceProduct());
            item.setQuantity(product.getAmount());
            item.setOrderId(orderDomain.getOrderId());

            orderItemEntities.add(item);
        }

        return orderItemEntities;
    }
}
