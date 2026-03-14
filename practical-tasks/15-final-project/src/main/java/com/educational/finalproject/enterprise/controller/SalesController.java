package com.educational.finalproject.enterprise.controller;

import com.educational.finalproject.enterprise.model.Order;
import com.educational.finalproject.enterprise.service.SalesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST контроллер для управления продажами.
 */
@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return salesService.getAllOrders();
    }

    @PostMapping("/orders")
    public Order placeOrder(@RequestBody Order order) {
        return salesService.placeOrder(order);
    }

    @PostMapping("/orders/{id}/cancel")
    public Order cancelOrder(@PathVariable Long id) {
        return salesService.cancelOrder(id);
    }

    @GetMapping("/orders/status")
    public List<Order> getByStatus(@RequestParam String status) {
        return salesService.getOrdersByStatus(status);
    }

    @PatchMapping("/orders/{id}/status")
    public Order updateStatus(@PathVariable Long id, @RequestParam String status) {
        return salesService.updateOrderStatus(id, status);
    }
}
