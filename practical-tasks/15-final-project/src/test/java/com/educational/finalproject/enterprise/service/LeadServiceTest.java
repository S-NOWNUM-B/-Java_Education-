package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.LeadDTO;
import com.educational.finalproject.enterprise.mapper.LeadMapper;
import com.educational.finalproject.enterprise.model.Lead;
import com.educational.finalproject.enterprise.repository.LeadRepository;
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
 * <p>Тестовый класс для LeadService.</p>
 * <p>Обеспечивает верификацию процессов управления потенциальными клиентами. 
 * Включает тесты на скоринг, квалификацию и обогащение данных.</p>
 * 
 * @author Antigravity
 */
public class LeadServiceTest {

    @Mock
    private LeadRepository leadRepository;

    @Mock
    private LeadMapper leadMapper;

    @InjectMocks
    private LeadService leadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * <p>Тест квалификации лида.</p>
     */
    @Test
    @DisplayName("Проверка процесса изменения статуса лида на 'QUALIFIED'")
    void testLeadQualification() {
        // Given
        Long leadId = 1L;
        Lead lead = new Lead();
        lead.setId(leadId);
        lead.setStatus("NEW");
        lead.setScore(0);

        when(leadRepository.findById(leadId)).thenReturn(Optional.of(lead));
        when(leadRepository.save(any(Lead.class))).thenReturn(lead);
        when(leadMapper.toDTO(any(Lead.class))).thenReturn(new LeadDTO());

        // When
        leadService.qualifyLead(leadId);

        // Then
        assertEquals("QUALIFIED", lead.getStatus(), "Статус лида должен стать QUALIFIED");
        assertTrue(lead.getScore() > 0, "Скоринг должен быть обновлен");
        verify(leadRepository, times(1)).save(lead);
    }

    /**
     * <p>Тест создания нового лида.</p>
     */
    @Test
    @DisplayName("Проверка сохранения нового лида с начальными параметрами")
    void testCreateLead() {
        // Given
        LeadDTO dto = new LeadDTO();
        dto.setFirstName("Ivan");
        dto.setLastName("Ivanov");
        
        Lead lead = new Lead();
        lead.setFirstName("Ivan");
        
        when(leadMapper.toEntity(dto)).thenReturn(lead);
        when(leadRepository.save(any(Lead.class))).thenReturn(lead);
        when(leadMapper.toDTO(any(Lead.class))).thenReturn(dto);

        // When
        LeadDTO result = leadService.createLead(dto);

        // Then
        assertNotNull(result);
        assertEquals("Ivan", result.getFirstName());
        verify(leadRepository, times(1)).save(any(Lead.class));
    }

    /**
     * <p>Тест обработки ошибок при поиске несуществующего лида.</p>
     */
    @Test
    @DisplayName("Проверка исключения при попытке квалификации отсутствующего лида")
    void testLeadNotFoundException() {
        // Given
        when(leadRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            leadService.qualifyLead(999L);
        }, "Должно выбрасываться исключение Lead not found");
    }
}
