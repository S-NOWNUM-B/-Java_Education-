package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.model.Order;
import com.educational.finalproject.enterprise.model.OrderItem;
import com.educational.finalproject.enterprise.model.Product;
import com.educational.finalproject.enterprise.repository.OrderRepository;
import com.educational.finalproject.enterprise.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис управления продажами (Sales Service).
 */
@Service
@SuppressWarnings("null")
public class SalesService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final WmsService wmsService;

    public SalesService(OrderRepository orderRepository, 
                        ProductRepository productRepository, 
                        WmsService wmsService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.wmsService = wmsService;
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Создание заказа с проверкой остатков.
     */
    @Transactional
    public Order placeOrder(Order order) {
        double total = 0;
        for (OrderItem item : order.getItems()) {
            Product product = productRepository.findBySku(item.getSku())
                    .orElseThrow(() -> new RuntimeException("Товар не найден: " + item.getSku()));
            
            if (product.getQuantityInStock() < item.getQuantity()) {
                throw new RuntimeException("Недостаточно товара: " + product.getName());
            }
            
            // Уменьшаем остаток через WMS
            wmsService.updateStock(item.getSku(), -item.getQuantity());
            
            item.setTotalPrice(item.getQuantity() * item.getUnitPrice());
            total += item.getTotalPrice();
        }
        
        order.setTotalAmount(total);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CREATED");
        
        return orderRepository.save(order);
    }

    @Transactional
    public Order cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Заказ не найден"));
        
        if ("CANCELLED".equals(order.getStatus())) {
            return order;
        }
        
        // Возврат товара на склад
        for (OrderItem item : order.getItems()) {
            wmsService.updateStock(item.getSku(), item.getQuantity());
        }
        
        order.setStatus("CANCELLED");
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    @Transactional
    public Order updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Заказ не найден"));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }
}
