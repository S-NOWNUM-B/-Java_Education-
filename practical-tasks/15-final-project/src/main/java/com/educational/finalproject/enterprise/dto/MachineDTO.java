package com.educational.finalproject.enterprise.dto;

import java.time.LocalDate;

/**
 * <p>Data Transfer Object для оборудования (MachineDTO).</p>
 * <p>Используется для инвентаризации и мониторинга работоспособности 
 * станков. Содержит ссылки на рабочий центр и дату ввода в эксплуатацию.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
public class MachineDTO {

    private Long id;
    private String name;
    private String modelNumber;
    private String serialNumber;
    private String status;
    private Long workCenterId;
    private String workCenterName; // Обогащенное поле
    private LocalDate purchaseDate;

    /**
     * Конструктор по умолчанию.
     */
    public MachineDTO() {}

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

    public Long getWorkCenterId() {
        return workCenterId;
    }

    public void setWorkCenterId(Long workCenterId) {
        this.workCenterId = workCenterId;
    }

    public String getWorkCenterName() {
        return workCenterName;
    }

    public void setWorkCenterName(String workCenterName) {
        this.workCenterName = workCenterName;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
