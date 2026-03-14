package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.model.Driver;
import com.educational.finalproject.enterprise.model.Route;
import com.educational.finalproject.enterprise.model.Shipment;
import com.educational.finalproject.enterprise.repository.DriverRepository;
import com.educational.finalproject.enterprise.repository.RouteRepository;
import com.educational.finalproject.enterprise.repository.ShipmentRepository;
import com.educational.finalproject.enterprise.mapper.RouteMapper;
import com.educational.finalproject.enterprise.dto.RouteDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * <p>Тестовый класс для RouteService.</p>
 * <p>Верифицирует алгоритмы планирования рейсов и назначения ресурсов. 
 * Проверяет корректность расчета топливных затрат и дистанции маршрута.</p>
 * 
 * @author Antigravity
 */
public class RouteServiceTest {

    @Mock
    private RouteRepository routeRepository;
    @Mock
    private DriverRepository driverRepository;
    @Mock
    private ShipmentRepository shipmentRepository;
    @Mock
    private RouteMapper routeMapper;

    @InjectMocks
    private RouteService routeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * <p>Тест добавления отгрузки в маршрут.</p>
     */
    @Test
    @DisplayName("Проверка добавления груза в маршрут и пересчета дистанции")
    void testAddShipmentToRoute() {
        // Given
        Long rId = 1L;
        Long sId = 2L;
        Route route = new Route("Test Route");
        route.setTotalDistanceKm(0.0);
        Shipment shipment = new Shipment();

        when(routeRepository.findById(rId)).thenReturn(Optional.of(route));
        when(shipmentRepository.findById(sId)).thenReturn(Optional.of(shipment));

        // When
        routeService.addShipmentToRoute(rId, sId);

        // Then
        assertTrue(route.getShipments().contains(shipment));
        assertEquals(150.0, route.getTotalDistanceKm()); // 0 + 150
        assertTrue(route.getEstimatedFuelConsumption() > 0);
        verify(routeRepository).save(route);
    }

    /**
     * <p>Тест назначения водителя.</p>
     */
    @Test
    @DisplayName("Проверка назначения доступного водителя на рейс")
    void testAssignDriver() {
        // Given
        Long rId = 1L;
        Long dId = 5L;
        Route route = new Route();
        Driver driver = new Driver();
        driver.setStatus("ON_DUTY");

        when(routeRepository.findById(rId)).thenReturn(Optional.of(route));
        when(driverRepository.findById(dId)).thenReturn(Optional.of(driver));

        // When
        routeService.assignDriver(rId, dId);

        // Then
        assertEquals(driver, route.getPrimaryDriver());
        verify(routeRepository).save(route);
    }

    /**
     * <p>Тест ошибки при назначении недоступного водителя.</p>
     */
    @Test
    @DisplayName("Проверка блокировки назначения водителя в статусе OFF_DUTY")
    void testAssignUnavailableDriver() {
        // Given
        Driver driver = new Driver();
        driver.setStatus("OFF_DUTY");
        when(routeRepository.findById(any())).thenReturn(Optional.of(new Route()));
        when(driverRepository.findById(any())).thenReturn(Optional.of(driver));

        // When & Then
        assertThrows(RuntimeException.class, () -> routeService.assignDriver(1L, 1L));
    }
}
