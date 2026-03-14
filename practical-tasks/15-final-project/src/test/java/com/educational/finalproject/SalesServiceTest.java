package com.educational.finalproject;

import com.educational.finalproject.enterprise.model.Order;
import com.educational.finalproject.enterprise.model.OrderItem;
import com.educational.finalproject.enterprise.model.Product;
import com.educational.finalproject.enterprise.repository.OrderRepository;
import com.educational.finalproject.enterprise.repository.ProductRepository;
import com.educational.finalproject.enterprise.service.SalesService;
import com.educational.finalproject.enterprise.service.WmsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Тесты для SalesService.
 */
@SuppressWarnings("null")
class SalesServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private WmsService wmsService;
    
    private SalesService salesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        salesService = new SalesService(orderRepository, productRepository, wmsService);
    }

    @Test
    @DisplayName("Должен успешно оформлять заказ")
    void shouldPlaceOrderSuccessfully() {
        // Given
        Order order = new Order("ORD-001");
        OrderItem item = new OrderItem("SKU1", "Prod 1", 2, 50.0);
        order.addItem(item);
        
        Product product = new Product("SKU1", "Prod 1", 50.0);
        product.setQuantityInStock(10);

        when(productRepository.findBySku("SKU1")).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArguments()[0]);

        // When
        Order result = salesService.placeOrder(order);

        // Then
        assertNotNull(result);
        assertEquals(100.0, result.getTotalAmount());
        assertEquals("CREATED", result.getStatus());
        verify(wmsService).updateStock("SKU1", -2);
        verify(orderRepository).save(order);
    }

    @Test
    @DisplayName("Должен выбрасывать ошибку если товара недостаточно")
    void shouldThrowErrorWhenNotEnoughStock() {
        // Given
        Order order = new Order("ORD-001");
        OrderItem item = new OrderItem("SKU1", "Prod 1", 20, 50.0);
        order.addItem(item);
        
        Product product = new Product("SKU1", "Prod 1", 50.0);
        product.setQuantityInStock(10);

        when(productRepository.findBySku("SKU1")).thenReturn(Optional.of(product));

        // When & Then
        assertThrows(RuntimeException.class, () -> salesService.placeOrder(order));
        verify(wmsService, never()).updateStock(anyString(), anyInt());
    }

    @Test
    @DisplayName("Должен отменять заказ и возвращать остатки")
    void shouldCancelOrderAndReturnStock() {
        // Given
        Order order = new Order("ORD-001");
        order.setId(1L);
        order.setStatus("CREATED");
        OrderItem item = new OrderItem("SKU1", "Prod 1", 2, 50.0);
        order.addItem(item);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArguments()[0]);

        // When
        Order result = salesService.cancelOrder(1L);

        // Then
        assertEquals("CANCELLED", result.getStatus());
        verify(wmsService).updateStock("SKU1", 2);
        verify(orderRepository).save(order);
    }

    @Test
    @DisplayName("Должен находить заказы по статусу")
    void shouldFindOrdersByStatus() {
        salesService.getOrdersByStatus("CREATED");
        verify(orderRepository).findByStatus("CREATED");
    }
}
