package com.educational.finalproject.enterprise.dto;

/**
 * <p>Data Transfer Object для рабочего центра (WorkCenterDTO).</p>
 * <p>Предназначен для мониторинга состояния производственных линий. 
 * Включает информацию о загрузке и местоположении центра.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
public class WorkCenterDTO {

    private Long id;
    private String name;
    private String description;
    private double capacityPerHour;
    private String locationCode;
    private String supervisorName;
    private boolean isActive;

    /**
     * Конструктор по умолчанию.
     */
    public WorkCenterDTO() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCapacityPerHour() {
        return capacityPerHour;
    }

    public void setCapacityPerHour(double capacityPerHour) {
        this.capacityPerHour = capacityPerHour;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
