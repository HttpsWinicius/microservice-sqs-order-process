package com.sqs.microservice.process.service;

import com.sqs.microservice.process.domain.dto.ProductDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderCalculationServiceImplTest {

    private static final double EXPECTED_PRINCE_SUCCESS = 126.88;
    private static final double EXPECTED_AMOUNT_SUCCESS = 14;

    private final OrderCalculationServiceImpl orderCalculationService = new OrderCalculationServiceImpl();

    @Test
    void testCalculateTotalPrice() {
        List<ProductDto> products = createProductList();

        double totalPrice = orderCalculationService.calculateTotalPrice(products);

        assertEquals(EXPECTED_PRINCE_SUCCESS, totalPrice, "O preço total está incorreto.");
    }

    @Test
    void testCalculateTotalProduct() {
        List<ProductDto> products = createProductList();

        int totalProducts = orderCalculationService.calculateTotalProduct(products);


        assertEquals(EXPECTED_AMOUNT_SUCCESS, totalProducts, "O total de produtos está incorreto.");
    }

    @Test
    void testCalculateTotalPriceEmptyList() {
        List<ProductDto> products = Arrays.asList();

        double totalPrice = orderCalculationService.calculateTotalPrice(products);
        
        assertEquals(0.0, totalPrice, "O preço total para uma lista vazia deve ser 0.");
    }

    @Test
    void testCalculateTotalProducEmptyList() {
        List<ProductDto> products = Arrays.asList();

        int totalProducts = orderCalculationService.calculateTotalProduct(products);

        assertEquals(0, totalProducts, "O total de produtos para uma lista vazia deve ser 0.");
    }


    private List<ProductDto> createProductList() {
        ProductDto product1 = new ProductDto("Cerveja A", 15.50, 2);
        ProductDto product2 = new ProductDto("Cerveja B", 7.99, 12);
        return Arrays.asList(product1, product2);
    }
}
