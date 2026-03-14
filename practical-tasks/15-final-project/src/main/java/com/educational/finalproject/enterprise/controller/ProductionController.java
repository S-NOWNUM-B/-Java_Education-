package com.educational.finalproject.enterprise.controller;

import com.educational.finalproject.enterprise.dto.ProductionOrderDTO;
import com.educational.finalproject.enterprise.service.ProductionOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>REST контроллер для управления производством (ProductionController).</p>
 * <p>Обеспечивает внешний интерфейс для создания заказов, 
 * отслеживания их статуса и управления приоритетами выполнения 
 * в рамках производственного цикла предприятия.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/production")
public class ProductionController {

    private final ProductionOrderService productionOrderService;

    public ProductionController(ProductionOrderService productionOrderService) {
        this.productionOrderService = productionOrderService;
    }

    /**
     * Создание нового производственного заказа.
     * @param productId ID продукта для производства
     * @param quantity Требуемое количество
     * @return DTO созданного заказа
     */
    @PostMapping("/orders")
    public ResponseEntity<ProductionOrderDTO> createOrder(@RequestParam Long productId, 
                                                         @RequestParam int quantity) {
        ProductionOrderDTO order = productionOrderService.createOrder(productId, quantity);
        return ResponseEntity.ok(order);
    }

    /**
     * Получение списка всех активных заказов.
     * @return Список заказов в работе
     */
    @GetMapping("/orders/active")
    public ResponseEntity<List<ProductionOrderDTO>> getActiveOrders() {
        return ResponseEntity.ok(productionOrderService.getActiveOrders());
    }

    /**
     * Продвижение статуса заказа (например, из OPEN в IN_PROGRESS).
     * @param orderId ID заказа
     * @param nextStatus Новый статус
     * @return Подтверждение операции
     */
    @PutMapping("/orders/{orderId}/advance")
    public ResponseEntity<String> advanceOrder(@PathVariable Long orderId, 
                                              @RequestParam String nextStatus) {
        productionOrderService.advanceStatus(orderId, nextStatus);
        return ResponseEntity.ok("Order status updated to " + nextStatus);
    }

    /**
     * Обновление приоритета заказа.
     * @param orderId ID заказа
     * @param priority Число от 1 до 10
     * @return Подтверждение обновления
     */
    @PatchMapping("/orders/{orderId}/priority")
    public ResponseEntity<Void> updatePriority(@PathVariable Long orderId, 
                                              @RequestParam int priority) {
        productionOrderService.updatePriority(orderId, priority);
        return ResponseEntity.noContent().build();
    }
}
