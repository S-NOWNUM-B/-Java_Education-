package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.RouteDTO;
import com.educational.finalproject.enterprise.mapper.RouteMapper;
import com.educational.finalproject.enterprise.model.Driver;
import com.educational.finalproject.enterprise.model.Route;
import com.educational.finalproject.enterprise.model.Shipment;
import com.educational.finalproject.enterprise.repository.DriverRepository;
import com.educational.finalproject.enterprise.repository.RouteRepository;
import com.educational.finalproject.enterprise.repository.ShipmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Сервис планирования маршрутов (RouteService).</p>
 * <p>Класс отвечает за оптимизацию транспортных потоков. 
 * Он реализует логику формирования рейсов, назначения водителей 
 * и расчета эксплуатационных расходов (топливо, пошлины).</p>
 * 
 * <p>Алгоритмы сервиса имитируют работу транспортного диспетчера, 
 * позволяя эффективно распределять заказы по имеющемуся автопарку.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final DriverRepository driverRepository;
    private final ShipmentRepository shipmentRepository;
    private final RouteMapper routeMapper;

    /**
     * Конструктор сервиса маршрутов.
     */
    public RouteService(RouteRepository routeRepository, 
                        DriverRepository driverRepository, 
                        ShipmentRepository shipmentRepository, 
                        RouteMapper routeMapper) {
        this.routeRepository = routeRepository;
        this.driverRepository = driverRepository;
        this.shipmentRepository = shipmentRepository;
        this.routeMapper = routeMapper;
    }

    /**
     * <p>Создает новый план рейса.</p>
     * @param name Название маршрута
     * @param departure Время отбытия
     * @return RouteDTO
     */
    @Transactional
    public RouteDTO createRoute(String name, LocalDateTime departure) {
        Route route = new Route(name);
        route.setDepartureTime(departure);
        route.setStatus("PLANNED");
        
        Route saved = routeRepository.save(route);
        return routeMapper.toDTO(saved);
    }

    /**
     * <p>Добавляет отгрузку в существующий маршрут.</p>
     * @param routeId ID маршрута
     * @param shipmentId ID отгрузки
     */
    @Transactional
    public void addShipmentToRoute(Long routeId, Long shipmentId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Маршрут не найден"));
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Отгрузка не найдена"));
        
        // В реальной системе здесь была бы проверка весогабаритных ограничений ТС
        route.getShipments().add(shipment);
        
        // Обновляем дистанцию (имитация)
        route.setTotalDistanceKm(route.getTotalDistanceKm() + 150.0);
        
        // Пересчитываем расходы
        calculateRouteCosts(route);
        
        routeRepository.save(route);
    }

    /**
     * <p>Внутренний метод для расчета финансовых показателей рейса.</p>
     */
    private void calculateRouteCosts(Route route) {
        double fuelRate = 0.35; // 35 литров на 100 км
        double fuelPrice = 55.0;
        
        double fuelNeeded = (route.getTotalDistanceKm() / 100.0) * fuelRate;
        route.setEstimatedFuelConsumption(fuelNeeded);
        route.setEstimatedTollCosts(route.getTotalDistanceKm() * 2.5); // 2.5 руб/км
    }

    /**
     * <p>Назначает водителя на маршрут.</p>
     * @param routeId ID маршрута
     * @param driverId ID водителя
     */
    @Transactional
    public void assignDriver(Long routeId, Long driverId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Маршрут не найден"));
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Водитель не найден"));
        
        if (!"ON_DUTY".equals(driver.getStatus())) {
            throw new RuntimeException("Водитель недоступен для назначения");
        }
        
        route.setPrimaryDriver(driver);
        routeRepository.save(route);
    }

    /**
     * <p>Завершает маршрут и освобождает ресурсы (водителя и ТС).</p>
     * @param id ID маршрута
     */
    @Transactional
    public void completeRoute(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Маршрут не найден"));
        
        route.setStatus("COMPLETED");
        route.setArrivalTime(LocalDateTime.now());
        
        // Обновляем статусы всех отгрузок в маршруте
        for (Shipment s : route.getShipments()) {
            s.setStatus("DELIVERED");
            shipmentRepository.save(s);
        }
        
        routeRepository.save(route);
    }
}
