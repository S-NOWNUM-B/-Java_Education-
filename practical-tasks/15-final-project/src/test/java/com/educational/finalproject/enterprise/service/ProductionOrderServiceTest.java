package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.ProductionOrderDTO;
import com.educational.finalproject.enterprise.mapper.ProductionOrderMapper;
import com.educational.finalproject.enterprise.model.ProductionOrder;
import com.educational.finalproject.enterprise.repository.ProductionOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * <p>Модульные тесты для ProductionOrderService.</p>
 * <p>Тестирование включает сценарии создания заказов, управления статусами 
 * и бизнес-валидацию приоритетов. Используется Mockito для изоляции 
 * зависимости от репозитория и маппера.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование сервиса производственных заказов")
class ProductionOrderServiceTest {

    @Mock
    private ProductionOrderRepository repository;

    @Mock
    private ProductionOrderMapper mapper;

    @InjectMocks
    private ProductionOrderService service;

    private ProductionOrder sampleOrder;
    private ProductionOrderDTO sampleDTO;

    @BeforeEach
    void setUp() {
        sampleOrder = new ProductionOrder();
        sampleOrder.setId(1L);
        sampleOrder.setOrderNumber("PRD-UNIT-TEST");
        sampleOrder.setProductId(101L);
        sampleOrder.setQuantity(50);
        sampleOrder.setStatus("OPEN");
        sampleOrder.setPriority(1);

        sampleDTO = new ProductionOrderDTO();
        sampleDTO.setId(1L);
        sampleDTO.setOrderNumber("PRD-UNIT-TEST");
        sampleDTO.setStatus("OPEN");
    }

    @Test
    @DisplayName("Создание заказа с корректными параметрами")
    void testCreateOrderSuccess() {
        // Arrange
        when(repository.save(any(ProductionOrder.class))).thenReturn(sampleOrder);
        when(mapper.toDTO(any(ProductionOrder.class))).thenReturn(sampleDTO);

        // Act
        ProductionOrderDTO result = service.createOrder(101L, 50);

        // Assert
        assertNotNull(result);
        assertEquals("PRD-UNIT-TEST", result.getOrderNumber());
        verify(repository, times(1)).save(any(ProductionOrder.class));
    }

    @Test
    @DisplayName("Обновление статуса заказа")
    void testAdvanceStatus() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(sampleOrder));

        // Act
        service.advanceStatus(1L, "IN_PROGRESS");

        // Assert
        assertEquals("IN_PROGRESS", sampleOrder.getStatus());
        verify(repository, times(1)).save(sampleOrder);
    }

    @Test
    @DisplayName("Ошибка при поиске несуществующего заказа")
    void testAdvanceStatusNotFound() {
        // Arrange
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> service.advanceStatus(99L, "IN_PROGRESS"));
    }

    @Test
    @DisplayName("Успешное обновление приоритета")
    void testUpdatePrioritySuccess() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(sampleOrder));

        // Act
        service.updatePriority(1L, 5);

        // Assert
        assertEquals(5, sampleOrder.getPriority());
        verify(repository, times(1)).save(sampleOrder);
    }

    @Test
    @DisplayName("Валидация диапазона приоритета")
    void testUpdatePriorityInvalidValue() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.updatePriority(1L, 11));
        assertThrows(IllegalArgumentException.class, () -> service.updatePriority(1L, 0));
    }

    @Test
    @DisplayName("Получение списка активных заказов")
    void testGetActiveOrders() {
        // Arrange
        List<ProductionOrder> orders = Arrays.asList(sampleOrder);
        when(repository.findByStatus("IN_PROGRESS")).thenReturn(orders);
        when(mapper.toDTOList(orders)).thenReturn(Arrays.asList(sampleDTO));

        // Act
        List<ProductionOrderDTO> result = service.getActiveOrders();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
