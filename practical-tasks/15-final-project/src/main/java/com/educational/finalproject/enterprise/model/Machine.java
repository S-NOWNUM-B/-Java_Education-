package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;

/**
 * <p>Сущность промышленного оборудования (Machine).</p>
 * <p>Класс представляет собой описание конкретного станка или агрегата, 
 * закрепленного за рабочим центром. Отслеживает текущее техническое состояние 
 * и серийные номера для учета амортизации.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Entity
@Table(name = "machines")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "model_number")
    private String modelNumber;

    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    @Column(name = "status")
    private String status; // OPERATIONAL, MAINTENANCE, BROKEN

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_center_id")
    private WorkCenter workCenter;

    @Column(name = "purchase_date")
    private java.time.LocalDate purchaseDate;

    /**
     * Конструктор по умолчанию.
     */
    public Machine() {}

    // --- MANUAL GETTERS AND SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WorkCenter getWorkCenter() {
        return workCenter;
    }

    public void setWorkCenter(WorkCenter workCenter) {
        this.workCenter = workCenter;
    }

    public java.time.LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(java.time.LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
