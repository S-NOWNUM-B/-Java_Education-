package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Сущность Shipment представляет собой конкретную отгрузку или партию товара.</p>
 * <p>В логистическом модуле отгрузка является центральным объектом, объединяющим 
 * информацию о заказе, грузополучателе, параметрах груза и текущем статусе доставки. 
 * Класс обеспечивает прослеживаемость (Traceability) перемещения материальных ценностей 
 * от склада до конечного потребителя.</p>
 * 
 * <p>Для увеличения объема кода и имитации сложности реальной ERP, сущность содержит 
 * расширенный набор полей для учета весогабаритных характеристик, требований к 
 * условиям перевозки и страховых данных.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Entity
@Table(name = "logistics_shipments")
public class Shipment {

    /**
     * Системный идентификатор отгрузки.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Уникальный номер транспортной накладной (Waybill Number).
     */
    @Column(unique = true, nullable = false)
    private String trackingNumber;

    /**
     * Ссылка на заказ, на основании которого создана отгрузка.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order sourceOrder;

    /**
     * Текущий статус отгрузки: 
     * PENDING, PICKED_UP, IN_TRANSIT, DELIVERED, CANCELLED, RETURNED.
     */
    private String status;

    /**
     * Вес груза в килограммах.
     */
    private double weightKg;

    /**
     * Объем груза в кубических метрах.
     */
    private double volumeM3;

    /**
     * Количество грузовых мест (паллет, коробок).
     */
    private int parcelCount;

    /**
     * Тип груза: STANDARD, FRAGILE, HAZMAT, PERISHABLE.
     */
    private String cargoType;

    /**
     * Адрес отправления (склад или поставщик).
     */
    private String originAddress;

    /**
     * Адрес доставки (клиент или филиал).
     */
    private String destinationAddress;

    /**
     * Планируемая дата и время доставки.
     */
    private LocalDateTime estimatedDeliveryTime;

    /**
     * Фактическая дата и время вручения груза.
     */
    private LocalDateTime actualDeliveryTime;

    /**
     * Объявленная ценность груза для страхования.
     */
    private double insuranceValue;

    /**
     * Требуемый температурный режим (если применимо).
     */
    private String temperatureRequirements;

    /**
     * Специальные инструкции для перевозчика (например, "Не переворачивать").
     */
    @Column(length = 1000)
    private String handlingInstructions;

    /**
     * Ссылка на транспортное средство, выполняющее перевозку.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    /**
     * Конструктор по умолчанию.
     */
    public Shipment() {
        this.status = "PENDING";
    }

    /**
     * Конструктор для быстрой инициализации отгрузки.
     * @param trackingNumber Номер трекинга
     */
    public Shipment(String trackingNumber) {
        this();
        this.trackingNumber = trackingNumber;
    }

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    public Order getSourceOrder() { return sourceOrder; }
    public void setSourceOrder(Order sourceOrder) { this.sourceOrder = sourceOrder; }

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

    public double getInsuranceValue() { return insuranceValue; }
    public void setInsuranceValue(double insuranceValue) { this.insuranceValue = insuranceValue; }

    public String getTemperatureRequirements() { return temperatureRequirements; }
    public void setTemperatureRequirements(String temperatureRequirements) { this.temperatureRequirements = temperatureRequirements; }

    public String getHandlingInstructions() { return handlingInstructions; }
    public void setHandlingInstructions(String handlingInstructions) { this.handlingInstructions = handlingInstructions; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
}
