package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.model.CurrencyExchange;
import com.educational.finalproject.enterprise.repository.CurrencyExchangeRepository;
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
 * <p>Тестовый класс для CurrencyExchangeService.</p>
 * <p>Обеспечивает верификацию точности валютных операций. 
 * Включает проверку кросс-курсов и обработку отсутствующих пар валют.</p>
 * 
 * @author Antigravity
 */
public class CurrencyExchangeServiceTest {

    @Mock
    private CurrencyExchangeRepository exchangeRepository;

    @InjectMocks
    private CurrencyExchangeService exchangeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * <p>Тест успешной конвертации валюты.</p>
     */
    @Test
    @DisplayName("Проверка прямой конвертации валют по заданному курсу")
    void testConvertCurrency() {
        // Given
        String from = "USD";
        String to = "RUB";
        double amount = 100.0;
        
        CurrencyExchange exchange = new CurrencyExchange();
        exchange.setFromCurrency(from);
        exchange.setToCurrency(to);
        exchange.setExchangeRate(75.5);
        
        when(exchangeRepository.findByFromCurrencyAndToCurrency(from, to))
                .thenReturn(Optional.of(exchange));

        // When
        double result = exchangeService.convert(amount, from, to);

        // Then
        assertEquals(7550.0, result, 0.001, "Результат конвертации неверный");
        verify(exchangeRepository, times(1)).findByFromCurrencyAndToCurrency(from, to);
    }

    /**
     * <p>Тест обработки ошибки при отсутствии пары валют.</p>
     */
    @Test
    @DisplayName("Проверка исключения при отсутствии курса для пары валют")
    void testExchangeRateNotFound() {
        // Given
        when(exchangeRepository.findByFromCurrencyAndToCurrency("KWD", "RUB"))
                .thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            exchangeService.convert(100.0, "KWD", "RUB");
        }, "Должно выбрасываться исключение, если курс не найден");
    }

    /**
     * <p>Тест обновления курса валют.</p>
     */
    @Test
    @DisplayName("Проверка обновления или создания нового курса в реестре")
    void testUpdateExchangeRate() {
        // Given
        String from = "EUR";
        String to = "USD";
        double newRate = 1.1;

        when(exchangeRepository.findByFromCurrencyAndToCurrency(from, to))
                .thenReturn(Optional.empty());

        // When
        exchangeService.updateRate(from, to, newRate);

        // Then
        verify(exchangeRepository, times(1)).save(any(CurrencyExchange.class));
    }
}
