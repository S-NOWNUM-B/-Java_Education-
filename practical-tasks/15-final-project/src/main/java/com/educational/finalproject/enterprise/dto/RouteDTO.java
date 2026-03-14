package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Data Transfer Object для маршрута (RouteDTO).</p>
 * <p>Объект для передачи данных о плане рейса. Включает агрегированные данные 
 * о количестве отгрузок и суммарной стоимости расходов на маршрут.</p>
 * 
 * @author Antigravity
 */
public class RouteDTO {

    private Long id;
    private String routeName;
    private double totalDistanceKm;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String status;
    private List<Long> shipmentIds;
    private String driverName;
    private double estimatedFuelConsumption;
    private double estimatedTollCosts;
    private String waypoints;

    /**
     * Конструктор по умолчанию.
     */
    public RouteDTO() {}

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

    public List<Long> getShipmentIds() { return shipmentIds; }
    public void setShipmentIds(List<Long> shipmentIds) { this.shipmentIds = shipmentIds; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public double getEstimatedFuelConsumption() { return estimatedFuelConsumption; }
    public void setEstimatedFuelConsumption(double estimatedFuelConsumption) { this.estimatedFuelConsumption = estimatedFuelConsumption; }

    public double getEstimatedTollCosts() { return estimatedTollCosts; }
    public void setEstimatedTollCosts(double estimatedTollCosts) { this.estimatedTollCosts = estimatedTollCosts; }

    public String getWaypoints() { return waypoints; }
    public void setWaypoints(String waypoints) { this.waypoints = waypoints; }
}
