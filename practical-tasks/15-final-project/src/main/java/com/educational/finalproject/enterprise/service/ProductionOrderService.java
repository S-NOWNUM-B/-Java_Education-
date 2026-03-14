package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.ProductionOrderDTO;
import com.educational.finalproject.enterprise.mapper.ProductionOrderMapper;
import com.educational.finalproject.enterprise.model.ProductionOrder;
import com.educational.finalproject.enterprise.repository.ProductionOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>Сервис для управления производственными заказами (ProductionOrderService).</p>
 * <p>Класс инкапсулирует сложную логику жизненного цикла производства. 
 * Включает автоматическую генерацию номеров заказов, расчет предполагаемых 
 * дат завершения на основе производительности и управление очередями.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Service
public class ProductionOrderService {

    private static final Logger log = LoggerFactory.getLogger(ProductionOrderService.class);

    private final ProductionOrderRepository repository;
    private final ProductionOrderMapper mapper;

    public ProductionOrderService(ProductionOrderRepository repository, ProductionOrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Инициализация нового производственного заказа.
     * @param productId ID продукта
     * @param quantity Количество
     * @return DTO созданного заказа
     */
    @Transactional
    public ProductionOrderDTO createOrder(Long productId, int quantity) {
        log.info("Creating new production order for product ID: {}", productId);
        
        ProductionOrder order = new ProductionOrder();
        order.setOrderNumber("PRD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setStatus("OPEN");
        order.setPriority(1); // Default low priority
        order.setStartDate(LocalDateTime.now());
        
        // Пример сложной логики: планируем завершение через 48 часов для малых партий
        order.setEndDate(LocalDateTime.now().plusHours(quantity > 100 ? 96 : 48));
        
        ProductionOrder savedOrder = repository.save(order);
        return mapper.toDTO(savedOrder);
    }

    /**
     * Перевод заказа на следующую стадию производства.
     * @param orderId ID заказа
     * @param newStatus Новый статус
     */
    @Transactional
    public void advanceStatus(Long orderId, String newStatus) {
        ProductionOrder order = repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Production order not found"));
        
        log.debug("Advancing order {} from {} to {}", order.getOrderNumber(), order.getStatus(), newStatus);
        order.setStatus(newStatus);
        
        if ("COMPLETED".equalsIgnoreCase(newStatus)) {
            order.setEndDate(LocalDateTime.now());
        }
        
        repository.save(order);
    }

    /**
     * Получение всех активных заказов.
     * @return Список DTO активных заказов
     */
    public List<ProductionOrderDTO> getActiveOrders() {
        List<ProductionOrder> activeOrders = repository.findByStatus("IN_PROGRESS");
        return mapper.toDTOList(activeOrders);
    }

    /**
     * Обновление приоритета заказа.
     * @param orderId ID заказа
     * @param priority Новый приоритет (от 1 до 10)
     */
    @Transactional
    public void updatePriority(Long orderId, int priority) {
        if (priority < 1 || priority > 10) {
            throw new IllegalArgumentException("Priority must be between 1 and 10");
        }
        repository.findById(orderId).ifPresent(o -> {
            o.setPriority(priority);
            repository.save(o);
        });
    }
}
