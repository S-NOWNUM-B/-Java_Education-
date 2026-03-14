package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.MaintenanceLogDTO;
import com.educational.finalproject.enterprise.mapper.MachineMapper;
import com.educational.finalproject.enterprise.mapper.MaintenanceLogMapper;
import com.educational.finalproject.enterprise.model.Machine;
import com.educational.finalproject.enterprise.model.MaintenanceLog;
import com.educational.finalproject.enterprise.repository.MachineRepository;
import com.educational.finalproject.enterprise.repository.MaintenanceLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * <p>Модульные тесты для MaintenanceService.</p>
 * <p>Проверка логики технического обслуживания станков, управления 
 * их статусами и регистрации затрат в журнале работ.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование сервиса обслуживания оборудования")
class MaintenanceServiceTest {

    @Mock
    private MachineRepository machineRepository;

    @Mock
    private MaintenanceLogRepository logRepository;

    @Mock
    private MachineMapper machineMapper;

    @Mock
    private MaintenanceLogMapper logMapper;

    @InjectMocks
    private MaintenanceService service;

    private Machine sampleMachine;
    private MaintenanceLog sampleLog;

    @BeforeEach
    void setUp() {
        sampleMachine = new Machine();
        sampleMachine.setId(10L);
        sampleMachine.setName("Сварочный робот Kuka");
        sampleMachine.setStatus("OPERATIONAL");

        sampleLog = new MaintenanceLog();
        sampleLog.setId(500L);
        sampleLog.setMachine(sampleMachine);
        sampleLog.setDescription("Тестовое ТО");
    }

    @Test
    @DisplayName("Постановка станка на обслуживание")
    void testScheduleMaintenance() {
        // Arrange
        when(machineRepository.findById(10L)).thenReturn(Optional.of(sampleMachine));
        when(logRepository.save(any(MaintenanceLog.class))).thenReturn(sampleLog);
        when(logMapper.toDTO(any(MaintenanceLog.class))).thenReturn(new MaintenanceLogDTO());

        // Act
        MaintenanceLogDTO result = service.scheduleMaintenance(10L, "Иван Петров");

        // Assert
        assertNotNull(result);
        assertEquals("MAINTENANCE", sampleMachine.getStatus());
        verify(machineRepository, times(1)).save(sampleMachine);
    }

    @Test
    @DisplayName("Завершение обслуживания и возврат в строй")
    void testCompleteMaintenance() {
        // Arrange
        sampleMachine.setStatus("MAINTENANCE");
        when(logRepository.findById(500L)).thenReturn(Optional.of(sampleLog));

        // Act
        service.completeMaintenance(500L, 1500.0);

        // Assert
        assertEquals("OPERATIONAL", sampleMachine.getStatus());
        assertEquals(1500.0, sampleLog.getTotalCost());
        assertTrue(sampleLog.getDescription().contains("Работы завершены"));
        verify(machineRepository, times(1)).save(sampleMachine);
    }

    @Test
    @DisplayName("Ошибка при закрытии несуществующего лога")
    void testCompleteMaintenanceNotFound() {
        // Arrange
        when(logRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> service.completeMaintenance(999L, 0.0));
    }
}
