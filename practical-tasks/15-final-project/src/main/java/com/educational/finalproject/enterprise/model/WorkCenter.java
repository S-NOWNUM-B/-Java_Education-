package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;

/**
 * <p>Сущность рабочего центра (WorkCenter).</p>
 * <p>Класс описывает производственную единицу (цех, участок, линию), 
 * где выполняются операции над изделиями. Содержит данные о мощностях 
 * и ответственном персонале.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Entity
@Table(name = "work_centers")
public class WorkCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "capacity_per_hour")
    private double capacityPerHour;

    @Column(name = "location_code")
    private String locationCode;

    @Column(name = "supervisor_name")
    private String supervisorName;

    @Column(name = "is_active")
    private boolean isActive;

    /**
     * Конструктор по умолчанию.
     */
    public WorkCenter() {}

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
