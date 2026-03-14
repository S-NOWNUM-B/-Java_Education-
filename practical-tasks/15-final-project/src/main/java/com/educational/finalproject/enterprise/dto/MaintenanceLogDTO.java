package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для записей обслуживания (MaintenanceLogDTO).</p>
 * <p>Передает историю сервисных работ. Используется для планирования 
 * бюджета на ремонт и анализа надежности оборудования.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
public class MaintenanceLogDTO {

    private Long id;
    private Long machineId;
    private String machineName; // Обогащенное поле
    private LocalDateTime maintenanceDate;
    private String description;
    private String technicianName;
    private double totalCost;

    /**
     * Конструктор по умолчанию.
     */
    public MaintenanceLogDTO() {}

    // --- MANUAL GETTERS AND SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public LocalDateTime getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDateTime maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
