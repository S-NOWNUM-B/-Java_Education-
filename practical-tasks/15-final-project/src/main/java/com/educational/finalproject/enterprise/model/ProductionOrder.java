package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Сущность производственного заказа (ProductionOrder).</p>
 * <p>Класс представляет собой распоряжение на производство определенного 
 * количества продукции. Содержит информацию о сроках, приоритете 
 * и текущем состоянии выполнения работ на производственной линии.</p>
 * 
 * <p>Бизнес-логика обработки этого заказа включает в себя проверку 
 * наличия компонентов на складе и расчет загрузки рабочих центров.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Entity
@Table(name = "production_orders")
public class ProductionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", unique = true, nullable = false)
    private String orderNumber;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    private String status;

    @Column(name = "priority")
    private int priority;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "notes", length = 1000)
    private String notes;

    /**
     * Конструктор по умолчанию.
     */
    public ProductionOrder() {}

    /**
     * Конструктор с параметрами.
     * @param orderNumber Номер заказа
     */
    public ProductionOrder(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
