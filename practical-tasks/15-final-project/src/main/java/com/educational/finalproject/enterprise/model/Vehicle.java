package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Сущность Vehicle представляет собой транспортное средство из автопарка компании.</p>
 * <p>Логистическая подсистема должна вести строгий учет доступных ресурсов. 
 * Класс Vehicle хранит технические характеристики автомобиля, данные о грузоподъемности, 
 * информацию о государственном регистрационном номере и текущем техническом состоянии.</p>
 * 
 * <p>Каждое ТС закреплено за определенным типом перевозок и имеет сроки 
 * следующего технического обслуживания (Service Date).</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Entity
@Table(name = "logistics_vehicles")
public class Vehicle {

    /**
     * Системный идентификатор транспортного средства.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Государственный номер автомобиля.
     */
    @Column(unique = true, nullable = false)
    private String licensePlate;

    /**
     * Марка и модель транспортного средства (например, "Volvo FH16").
     */
    private String modelName;

    /**
     * Тип транспортного средства: TRUCK, VAN, REFRIGERATOR, CONTAINER_SHIPPER.
     */
    private String vehicleType;

    /**
     * Максимальная грузоподъемность в тоннах.
     */
    private double payloadCapacityTons;

    /**
     * Объем грузового отсека в кубических метрах.
     */
    private double cargoVolumeM3;

    /**
     * Текущий пробег автомобиля в километрах.
     */
    private long currentMileage;

    /**
     * Дата последнего технического обслуживания.
     */
    private LocalDateTime lastServiceDate;

    /**
     * Дата планируемого следующего технического обслуживания.
     */
    private LocalDateTime nextServiceDate;

    /**
     * Текущий статус готовности ТС: AVAILABLE, IN_ROUTE, UNDER_REPAIR, DECOMMISSIONED.
     */
    private String status;

    /**
     * Год выпуска транспортного средства.
     */
    private int manufacturingYear;

    /**
     * VIN-номер (идентификационный номер транспортного средства).
     */
    @Column(unique = true)
    private String vinNumber;

    /**
     * Конструктор по умолчанию.
     */
    public Vehicle() {
        this.status = "AVAILABLE";
    }

    /**
     * Конструктор для быстрой регистрации ТС.
     * @param licensePlate Госномер
     * @param type Тип
     */
    public Vehicle(String licensePlate, String type) {
        this();
        this.licensePlate = licensePlate;
        this.vehicleType = type;
    }

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public double getPayloadCapacityTons() { return payloadCapacityTons; }
    public void setPayloadCapacityTons(double payloadCapacityTons) { this.payloadCapacityTons = payloadCapacityTons; }

    public double getCargoVolumeM3() { return cargoVolumeM3; }
    public void setCargoVolumeM3(double cargoVolumeM3) { this.cargoVolumeM3 = cargoVolumeM3; }

    public long getCurrentMileage() { return currentMileage; }
    public void setCurrentMileage(long currentMileage) { this.currentMileage = currentMileage; }

    public LocalDateTime getLastServiceDate() { return lastServiceDate; }
    public void setLastServiceDate(LocalDateTime lastServiceDate) { this.lastServiceDate = lastServiceDate; }

    public LocalDateTime getNextServiceDate() { return nextServiceDate; }
    public void setNextServiceDate(LocalDateTime nextServiceDate) { this.nextServiceDate = nextServiceDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getManufacturingYear() { return manufacturingYear; }
    public void setManufacturingYear(int manufacturingYear) { this.manufacturingYear = manufacturingYear; }

    public String getVinNumber() { return vinNumber; }
    public void setVinNumber(String vinNumber) { this.vinNumber = vinNumber; }
}
