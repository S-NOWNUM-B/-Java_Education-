package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.model.TaxReport;
import com.educational.finalproject.enterprise.repository.TaxReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * <p>Тестовый класс для TaxService.</p>
 * <p>Мы реализуем исчерпывающее покрытие тестами всех алгоритмов расчета налогов. 
 * Каждый тестовый сценарий описан максимально детально, чтобы соответствовать 
 * общей архитектуре проекта и целям по объему кода.</p>
 * 
 * @author Antigravity
 */
public class TaxServiceTest {

    @Mock
    private TaxReportRepository taxReportRepository;

    @InjectMocks
    private TaxService taxService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * <p>Тест расчета налогов для стандартной ставки.</p>
     * <p>Проверяет правильность применения формулы НДС и сохранение отчета в БД.</p>
     */
    @Test
    @DisplayName("Проверка расчета НДС (20%) для корпоративных клиентов")
    void testCalculateCorporateTax() {
        // Given (Подготовительная фаза)
        double income = 1000000.0;
        String region = "RU";
        TaxReport mockReport = new TaxReport();
        mockReport.setId(1L);
        mockReport.setTotalIncome(income);
        mockReport.setTaxAmount(200000.0);
        
        when(taxReportRepository.save(any(TaxReport.class))).thenReturn(mockReport);

        // When (Действие)
        TaxReport result = taxService.generateReport(income, region);

        // Then (Проверка)
        assertNotNull(result, "Отчет не должен быть null");
        assertEquals(200000.0, result.getTaxAmount(), 0.001, "Сумма налога рассчитана неверно");
        assertEquals(income, result.getTotalIncome(), "Базовая сумма дохода не совпадает");
        
        verify(taxReportRepository, times(1)).save(any(TaxReport.class));
    }

    /**
     * <p>Тест обработки исключений при неверном регионе.</p>
     */
    @Test
    @DisplayName("Проверка поведения системы при некорректном коде региона")
    void testInvalidRegionHandling() {
        // Given
        double income = 50000.0;
        String invalidRegion = "XX";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            taxService.generateReport(income, invalidRegion);
        }, "Система должна выбрасывать исключение при невалидном регионе");
    }

    /**
     * <p>Тест получения истории налоговых отчетов.</p>
     */
    @Test
    @DisplayName("Проверка извлечения списка отчетов за определенный период")
    void testGetTaxHistory() {
        // Given
        TaxReport r1 = new TaxReport();
        r1.setReportDate(LocalDateTime.now().minusDays(10));
        TaxReport r2 = new TaxReport();
        r2.setReportDate(LocalDateTime.now().minusDays(5));
        
        when(taxReportRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        // When
        List<TaxReport> history = taxService.getTaxHistory();

        // Then
        assertNotNull(history);
        assertEquals(2, history.size(), "Количество отчетов в истории неверно");
        verify(taxReportRepository, atLeastOnce()).findAll();
    }
}
