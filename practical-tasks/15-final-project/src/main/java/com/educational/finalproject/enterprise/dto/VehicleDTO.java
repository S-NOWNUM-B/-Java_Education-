package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для транспортного средства (VehicleDTO).</p>
 * <p>Используется для передачи информации об автопарке. Содержит данные 
 * о пробеге, грузоподъемности и текущем статусе ТС.</p>
 * 
 * @author Antigravity
 */
public class VehicleDTO {

    private Long id;
    private String licensePlate;
    private String modelName;
    private String vehicleType;
    private double payloadCapacityTons;
    private double cargoVolumeM3;
    private long currentMileage;
    private LocalDateTime nextServiceDate;
    private String status;
    private String vinNumber;

    /**
     * Конструктор по умолчанию.
     */
    public VehicleDTO() {}

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

    public LocalDateTime getNextServiceDate() { return nextServiceDate; }
    public void setNextServiceDate(LocalDateTime nextServiceDate) { this.nextServiceDate = nextServiceDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getVinNumber() { return vinNumber; }
    public void setVinNumber(String vinNumber) { this.vinNumber = vinNumber; }
}
