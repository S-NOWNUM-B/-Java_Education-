package com.educational.finalproject.enterprise.controller;

import com.educational.finalproject.enterprise.dto.ShipmentDTO;
import com.educational.finalproject.enterprise.dto.RouteDTO;
import com.educational.finalproject.enterprise.service.ShipmentService;
import com.educational.finalproject.enterprise.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>REST контроллер логистических операций (LogisticsController).</p>
 * <p>Класс предоставляет API конечные точки для управления отгрузками 
 * и планирования транспортных маршрутов. Он объединяет функционал 
 * создания накладных и формирования рейсов в единый интерфейс управления.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@RestController
@RequestMapping("/api/logistics")
public class LogisticsController {

    private final ShipmentService shipmentService;
    private final RouteService routeService;

    /**
     * Конструктор контроллера.
     */
    public LogisticsController(ShipmentService shipmentService, RouteService routeService) {
        this.shipmentService = shipmentService;
        this.routeService = routeService;
    }

    /**
     * Инициализирует новую отгрузку.
     * @param orderId ID заказа
     * @param weight Вес
     * @param address Адрес
     * @return Созданная отгрузка
     */
    @PostMapping("/shipments")
    public ResponseEntity<ShipmentDTO> createShipment(
            @RequestParam Long orderId, 
            @RequestParam double weight, 
            @RequestParam String address) {
        return ResponseEntity.ok(shipmentService.initializeShipment(orderId, weight, address));
    }

    /**
     * Обновляет статус доставки и местоположение груза.
     * @param id ID отгрузки
     * @param status Статус
     * @param location Локация
     * @return Ответ 200 OK
     */
    @PutMapping("/shipments/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long id, 
            @RequestParam String status, 
            @RequestParam String location) {
        shipmentService.updateShipmentStatus(id, status, location);
        return ResponseEntity.ok().build();
    }

    /**
     * Создает новый пустой маршрут.
     * @param name Название
     * @return Созданный маршрут
     */
    @PostMapping("/routes")
    public ResponseEntity<RouteDTO> createRoute(@RequestParam String name) {
        return ResponseEntity.ok(routeService.createRoute(name, LocalDateTime.now()));
    }

    /**
     * Добавляет отгрузку в маршрут.
     * @param routeId ID маршрута
     * @param shipmentId ID отгрузки
     * @return Ответ 200 OK
     */
    @PostMapping("/routes/{routeId}/add-shipment")
    public ResponseEntity<Void> addShipment(@PathVariable Long routeId, @RequestParam Long shipmentId) {
        routeService.addShipmentToRoute(routeId, shipmentId);
        return ResponseEntity.ok().build();
    }

    /**
     * Завершает выполнение маршрута.
     * @param id ID маршрута
     * @return Ответ 200 OK
     */
    @PostMapping("/routes/{id}/complete")
    public ResponseEntity<Void> completeRoute(@PathVariable Long id) {
        routeService.completeRoute(id);
        return ResponseEntity.ok().build();
    }
}
