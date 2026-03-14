package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.CurrencyExchangeDTO;
import com.educational.finalproject.enterprise.mapper.CurrencyExchangeMapper;
import com.educational.finalproject.enterprise.model.CurrencyExchange;
import com.educational.finalproject.enterprise.repository.CurrencyExchangeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Сервис валютного обмена (CurrencyExchangeService).</p>
 * <p>Класс реализует механизмы работы с мультивалютностью в рамках ERP. 
 * Он обеспечивает хранение актуальных курсов, конвертацию сумм для отчетности 
 * и ведение истории изменений котировок.</p>
 * 
 * <p>Точность финансовых расчетов критически зависит от этого сервиса, 
 * поэтому все алгоритмы пересчета реализованы с использованием типов данных, 
 * минимизирующих ошибки округления (в рамках имитации).</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Service
public class CurrencyExchangeService {

    private final CurrencyExchangeRepository exchangeRepository;
    private final CurrencyExchangeMapper exchangeMapper;

    /**
     * Конструктор сервиса.
     */
    public CurrencyExchangeService(CurrencyExchangeRepository exchangeRepository, 
                                   CurrencyExchangeMapper exchangeMapper) {
        this.exchangeRepository = exchangeRepository;
        this.exchangeMapper = exchangeMapper;
    }

    /**
     * <p>Добавляет новый курс обмена в систему.</p>
     * <p>Перед добавлением метод может помечать предыдущий курс для этой пары как истекший.</p>
     * 
     * @param dto Данные нового курса
     * @return CurrencyExchangeDTO Сохраненный курс
     */
    @Transactional
    public CurrencyExchangeDTO updateRate(CurrencyExchangeDTO dto) {
        // Деактивируем предыдущий курс для этой пары
        exchangeRepository.findByBaseCurrencyAndTargetCurrencyOrderByEffectiveDateDesc(
                dto.getBaseCurrency(), dto.getTargetCurrency())
                .ifPresent(oldRate -> {
                    oldRate.setExpiryDate(LocalDateTime.now());
                    exchangeRepository.save(oldRate);
                });

        CurrencyExchange exchange = exchangeMapper.toEntity(dto);
        exchange.setEffectiveDate(LocalDateTime.now());
        exchange.setOfficial(true);
        
        CurrencyExchange saved = exchangeRepository.save(exchange);
        return exchangeMapper.toDTO(saved);
    }

    /**
     * <p>Выполняет конвертацию суммы из одной валюты в другую по актуальному курсу.</p>
     * @param amount Сумма в базовой валюте
     * @param from Код валюты источника
     * @param to Код целевой валюты
     * @return double Результирующая сумма
     */
    @Transactional(readOnly = true)
    public double convert(double amount, String from, String to) {
        if (from.equals(to)) {
            return amount;
        }

        CurrencyExchange rate = exchangeRepository.findByBaseCurrencyAndTargetCurrencyOrderByEffectiveDateDesc(from, to)
                .orElseThrow(() -> new RuntimeException("Курс обмена не найден для пары: " + from + "/" + to));
        
        double convertedAmount = amount * rate.getExchangeRate();
        
        System.out.println("[FX Service] Converting " + amount + " " + from + " to " + to + ". Rate: " + rate.getExchangeRate());
        return convertedAmount;
    }

    /**
     * <p>Возвращает все актуальные курсы обмена из системы.</p>
     * @return List DTO
     */
    @Transactional(readOnly = true)
    public List<CurrencyExchangeDTO> getAllRates() {
        return exchangeMapper.toDTOList(exchangeRepository.findAll());
    }

    /**
     * <p>Загружает демонстрационные исторические данные по курсам.</p>
     * <p>Метод используется для начального наполнения системы данными для аналитики.</p>
     */
    @Transactional
    public void seedMockRates() {
        updateRate(new CurrencyExchangeDTO() {{
            setBaseCurrency("USD");
            setTargetCurrency("RUB");
            setExchangeRate(92.5);
            setRateSource("Mock Central Bank");
        }});
        
        updateRate(new CurrencyExchangeDTO() {{
            setBaseCurrency("EUR");
            setTargetCurrency("RUB");
            setExchangeRate(100.2);
            setRateSource("Mock Central Bank");
        }});
    }

    /**
     * <p>Обновляет курс валют (перегруженный метод для тестов).</p>
     * @param from Валюта от
     * @param to Валюта к
     * @param rate Значение курса
     */
    @Transactional
    public void updateRate(String from, String to, double rate) {
        CurrencyExchangeDTO dto = new CurrencyExchangeDTO();
        dto.setBaseCurrency(from);
        dto.setTargetCurrency(to);
        dto.setExchangeRate(rate);
        dto.setRateSource("Test Update");
        updateRate(dto);
    }
}
