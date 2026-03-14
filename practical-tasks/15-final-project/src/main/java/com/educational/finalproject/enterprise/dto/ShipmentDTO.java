package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для отгрузки (ShipmentDTO).</p>
 * <p>Класс предназначен для передачи информации о логистических операциях. 
 * Включает агрегированные поля, такие как общее количество мест и 
 * ожидаемое время прибытия, адаптированные для пользовательского интерфейса.</p>
 * 
 * @author Antigravity
 */
public class ShipmentDTO {

    private Long id;
    private String trackingNumber;
    private Long orderId;
    private String status;
    private double weightKg;
    private double volumeM3;
    private int parcelCount;
    private String cargoType;
    private String originAddress;
    private String destinationAddress;
    private LocalDateTime estimatedDeliveryTime;
    private LocalDateTime actualDeliveryTime;
    private String licensePlate; // Из связанного Vehicle

    /**
     * Конструктор по умолчанию.
     */
    public ShipmentDTO() {}

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getWeightKg() { return weightKg; }
    public void setWeightKg(double weightKg) { this.weightKg = weightKg; }

    public double getVolumeM3() { return volumeM3; }
    public void setVolumeM3(double volumeM3) { this.volumeM3 = volumeM3; }

    public int getParcelCount() { return parcelCount; }
    public void setParcelCount(int parcelCount) { this.parcelCount = parcelCount; }

    public String getCargoType() { return cargoType; }
    public void setCargoType(String cargoType) { this.cargoType = cargoType; }

    public String getOriginAddress() { return originAddress; }
    public void setOriginAddress(String originAddress) { this.originAddress = originAddress; }

    public String getDestinationAddress() { return destinationAddress; }
    public void setDestinationAddress(String destinationAddress) { this.destinationAddress = destinationAddress; }

    public LocalDateTime getEstimatedDeliveryTime() { return estimatedDeliveryTime; }
    public void setEstimatedDeliveryTime(LocalDateTime estimatedDeliveryTime) { this.estimatedDeliveryTime = estimatedDeliveryTime; }

    public LocalDateTime getActualDeliveryTime() { return actualDeliveryTime; }
    public void setActualDeliveryTime(LocalDateTime actualDeliveryTime) { this.actualDeliveryTime = actualDeliveryTime; }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
}
