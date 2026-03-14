package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.ShipmentDTO;
import com.educational.finalproject.enterprise.mapper.ShipmentMapper;
import com.educational.finalproject.enterprise.model.Order;
import com.educational.finalproject.enterprise.model.Shipment;
import com.educational.finalproject.enterprise.repository.OrderRepository;
import com.educational.finalproject.enterprise.repository.ShipmentRepository;
import com.educational.finalproject.enterprise.repository.TrackingEventRepository;
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
 * <p>Тестовый класс для ShipmentService.</p>
 * <p>Проверяет корректность инициализации отгрузок и систему трекинга событий. 
 * Гарантирует, что каждое изменение статуса сопровождается записью в истории.</p>
 * 
 * @author Antigravity
 */
public class ShipmentServiceTest {

    @Mock
    private ShipmentRepository shipmentRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private TrackingEventRepository trackingRepository;
    @Mock
    private ShipmentMapper shipmentMapper;

    @InjectMocks
    private ShipmentService shipmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * <p>Тест инициализации отгрузки.</p>
     */
    @Test
    @DisplayName("Проверка создания новой отгрузки с регистрацией первого события")
    void testInitializeShipment() {
        // Given
        Long orderId = 100L;
        Order order = new Order();
        order.setId(orderId);
        
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(shipmentRepository.save(any(Shipment.class))).thenAnswer(i -> i.getArguments()[0]);
        when(shipmentMapper.toDTO(any(Shipment.class))).thenReturn(new ShipmentDTO());

        // When
        ShipmentDTO result = shipmentService.initializeShipment(orderId, 10.5, "Moscow, Arbat 1");

        // Then
        assertNotNull(result);
        verify(orderRepository).findById(orderId);
        verify(shipmentRepository).save(any(Shipment.class));
        verify(trackingRepository).save(any()); // Проверка регистрации события
    }

    /**
     * <p>Тест обновления статуса.</p>
     */
    @Test
    @DisplayName("Проверка обновления статуса отгрузки и записи в таймлайн")
    void testUpdateShipmentStatus() {
        // Given
        Long id = 1L;
        Shipment shipment = new Shipment();
        shipment.setId(id);
        shipment.setStatus("PENDING");

        when(shipmentRepository.findById(id)).thenReturn(Optional.of(shipment));

        // When
        shipmentService.updateShipmentStatus(id, "IN_TRANSIT", "Warehouse A");

        // Then
        assertEquals("IN_TRANSIT", shipment.getStatus());
        verify(shipmentRepository).save(shipment);
        verify(trackingRepository).save(any());
    }
}
