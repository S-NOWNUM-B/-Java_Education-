package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Сущность лога технического обслуживания (MaintenanceLog).</p>
 * <p>Класс фиксирует историю ремонтов и профилактических работ 
 * для оборудования. Содержит данные о стоимости работ и 
 * ответственном исполнителе.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Entity
@Table(name = "maintenance_logs")
public class MaintenanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id")
    private Machine machine;

    @Column(name = "maintenance_date")
    private LocalDateTime maintenanceDate;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "technician_name")
    private String technicianName;

    @Column(name = "total_cost")
    private double totalCost;

    /**
     * Конструктор по умолчанию.
     */
    public MaintenanceLog() {}

    // --- MANUAL GETTERS AND SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
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
