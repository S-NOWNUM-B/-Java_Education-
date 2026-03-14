package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.model.Opportunity;
import com.educational.finalproject.enterprise.repository.OpportunityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * <p>Тестовый класс для OpportunityService.</p>
 * <p>Проверяет механику работы с воронкой продаж. 
 * Включает тесты на изменение стадий сделки и расчет вероятностей закрытия.</p>
 * 
 * @author Antigravity
 */
public class OpportunityServiceTest {

    @Mock
    private OpportunityRepository opportunityRepository;

    @InjectMocks
    private OpportunityService opportunityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * <p>Тест продвижения сделки по воронке.</p>
     */
    @Test
    @DisplayName("Проверка перехода сделки на стадию 'NEGOTIATION'")
    void testMoveToNegotiation() {
        // Given
        Long oppId = 1L;
        Opportunity opportunity = new Opportunity();
        opportunity.setId(oppId);
        opportunity.setStage("PROSPECTING");
        opportunity.setProbability(10.0);

        when(opportunityRepository.findById(oppId)).thenReturn(Optional.of(opportunity));

        // When
        opportunityService.moveToStage(oppId, "NEGOTIATION");

        // Then
        assertEquals("NEGOTIATION", opportunity.getStage(), "Стадия должна измениться");
        assertTrue(opportunity.getProbability() > 10.0, "Вероятность должна вырасти");
        verify(opportunityRepository, times(1)).save(opportunity);
    }

    /**
     * <p>Тест закрытия сделки (Win).</p>
     */
    @Test
    @DisplayName("Проверка успешного закрытия сделки с вероятностью 100%")
    void testWinOpportunity() {
        // Given
        Long oppId = 1L;
        Opportunity opportunity = new Opportunity();
        opportunity.setId(oppId);
        
        when(opportunityRepository.findById(oppId)).thenReturn(Optional.of(opportunity));

        // When
        opportunityService.closeAsWon(oppId);

        // Then
        assertEquals("WON", opportunity.getStage());
        assertEquals(100.0, opportunity.getProbability());
        verify(opportunityRepository, times(1)).save(opportunity);
    }

    /**
     * <p>Тест расчета прогноза воронки.</p>
     */
    @Test
    @DisplayName("Проверка агрегированного расчета ожидаемого дохода")
    void testCalculateForecast() {
        // Given
        Opportunity o1 = new Opportunity();
        o1.setExpectedRevenue(1000.0);
        o1.setProbability(50.0);

        Opportunity o2 = new Opportunity();
        o2.setExpectedRevenue(2000.0);
        o2.setProbability(10.0);

        when(opportunityRepository.findAll()).thenReturn(java.util.Arrays.asList(o1, o2));

        // When
        double forecast = opportunityService.calculatePipelineWeightedRevenue();

        // Then
        // (1000 * 0.5) + (2000 * 0.1) = 500 + 200 = 700
        assertEquals(700.0, forecast, 0.001);
    }
}
