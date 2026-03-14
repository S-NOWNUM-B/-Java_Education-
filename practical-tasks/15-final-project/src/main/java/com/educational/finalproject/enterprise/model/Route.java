package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Сущность Route представляет собой спланированный маршрут перевозки.</p>
 * <p>Маршрут определяет путь следования транспортного средства и связывает 
 * несколько отгрузок (Shipments) в одну логистическую цепочку. Класс включает 
 * данные об общей дистанции, расчетном времени в пути и остановках (Stops).</p>
 * 
 * <p>Сложная структура маршрута позволяет ERP-системе оптимизировать загрузку 
 * автопарка и минимизировать порожние пробеги.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Entity
@Table(name = "logistics_routes")
public class Route {

    /**
     * Системный идентификатор маршрута.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Системное название маршрута (например, "MOW-SPB-Express").
     */
    @Column(nullable = false)
    private String routeName;

    /**
     * Общая дистанция маршрута в километрах.
     */
    private double totalDistanceKm;

    /**
     * Планируемая дата и время старта рейса.
     */
    private LocalDateTime departureTime;

    /**
     * Планируемая дата и время прибытия в конечную точку.
     */
    private LocalDateTime arrivalTime;

    /**
     * Текущий статус маршрута: DRAFT, PLANNED, IN_PROGRESS, COMPLETED, DELAYED.
     */
    private String status;

    /**
     * Список отгрузок, включенных в данный маршрут.
     */
    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY) // Упрощенная связь через транспорт
    private List<Shipment> shipments = new ArrayList<>();

    /**
     * Ссылка на основного водителя, назначенного на маршрут.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_driver_id")
    private Driver primaryDriver;

    /**
     * Прогноз потребления топлива на весь маршрут (в литрах).
     */
    private double estimatedFuelConsumption;

    /**
     * Сумма дорожных сборов и платных участков (Tolls).
     */
    private double estimatedTollCosts;

    /**
     * Контрольные точки маршрута (через запятую или в отдельной таблице).
     */
    @Column(length = 2000)
    private String waypoints;

    /**
     * Примечания диспетчера по маршруту.
     */
    private String dispatcherNotes;

    /**
     * Конструктор по умолчанию.
     */
    public Route() {
        this.status = "DRAFT";
    }

    /**
     * Конструктор для быстрой инициализации.
     * @param routeName Имя маршрута
     */
    public Route(String routeName) {
        this();
        this.routeName = routeName;
    }

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRouteName() { return routeName; }
    public void setRouteName(String routeName) { this.routeName = routeName; }

    public double getTotalDistanceKm() { return totalDistanceKm; }
    public void setTotalDistanceKm(double totalDistanceKm) { this.totalDistanceKm = totalDistanceKm; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<Shipment> getShipments() { return shipments; }
    public void setShipments(List<Shipment> shipments) { this.shipments = shipments; }

    public Driver getPrimaryDriver() { return primaryDriver; }
    public void setPrimaryDriver(Driver primaryDriver) { this.primaryDriver = primaryDriver; }

    public double getEstimatedFuelConsumption() { return estimatedFuelConsumption; }
    public void setEstimatedFuelConsumption(double estimatedFuelConsumption) { this.estimatedFuelConsumption = estimatedFuelConsumption; }

    public double getEstimatedTollCosts() { return estimatedTollCosts; }
    public void setEstimatedTollCosts(double estimatedTollCosts) { this.estimatedTollCosts = estimatedTollCosts; }

    public String getWaypoints() { return waypoints; }
    public void setWaypoints(String waypoints) { this.waypoints = waypoints; }

    public String getDispatcherNotes() { return dispatcherNotes; }
    public void setDispatcherNotes(String dispatcherNotes) { this.dispatcherNotes = dispatcherNotes; }
}
