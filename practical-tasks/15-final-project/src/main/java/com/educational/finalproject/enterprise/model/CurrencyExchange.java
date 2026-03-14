package com.educational.finalproject.enterprise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Сущность CurrencyExchange представляет собой запись о курсе обмена валют в системе.</p>
 * <p>Для финансового модуля ERP мультивалютность является обязательным требованием. 
 * Данный класс хранит историю изменений обменных курсов, позволяя системе выполнять 
 * пересчет транзакций и формирование отчетов в базовой валюте предприятия.</p>
 * 
 * <p>Класс поддерживает точность до 6 знаков после запятой, что соответствует стандартам 
 * банковских систем и крупных корпораций.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Entity
@Table(name = "currency_exchanges")
public class CurrencyExchange {

    /**
     * Системный идентификатор записи курса.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Базовая валюта (например, "USD").
     */
    @Column(nullable = false)
    private String baseCurrency;

    /**
     * Целевая валюта (например, "RUB").
     */
    @Column(nullable = false)
    private String targetCurrency;

    /**
     * Значение курса обмена (сколько единиц targetCurrency за 1 единицу baseCurrency).
     */
    @Column(nullable = false)
    private double exchangeRate;

    /**
     * Дата и время вступления курса в силу.
     */
    @Column(nullable = false)
    private LocalDateTime effectiveDate;

    /**
     * Дата и время окончания действия курса (null, если курс актуален).
     */
    private LocalDateTime expiryDate;

    /**
     * Источник данных курса (например, "Central Bank Of Russia", "Forex API").
     */
    private String rateSource;

    /**
     * Флаг, указывающий, является ли курс официально утвержденным руководством компании.
     */
    private boolean isOfficial;

    /**
     * Тип курса: SPOT, FORWARD, INTERNAL.
     */
    private String rateType;

    /**
     * Комментарии по расчету или корректировке курса.
     */
    @Column(length = 500)
    private String remarks;

    /**
     * Конструктор по умолчанию.
     */
    public CurrencyExchange() {
        this.effectiveDate = LocalDateTime.now();
        this.isOfficial = true;
    }

    /**
     * Конструктор для быстрой инициализации курса.
     * @param base Валюта от
     * @param target Валюта к
     * @param rate Значение
     */
    public CurrencyExchange(String base, String target, double rate) {
        this();
        this.baseCurrency = base;
        this.targetCurrency = target;
        this.exchangeRate = rate;
    }

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBaseCurrency() { return baseCurrency; }
    public void setBaseCurrency(String baseCurrency) { this.baseCurrency = baseCurrency; }

    public String getTargetCurrency() { return targetCurrency; }
    public void setTargetCurrency(String targetCurrency) { this.targetCurrency = targetCurrency; }

    public double getExchangeRate() { return exchangeRate; }
    public void setExchangeRate(double exchangeRate) { this.exchangeRate = exchangeRate; }

    public LocalDateTime getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(LocalDateTime effectiveDate) { this.effectiveDate = effectiveDate; }

    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }

    public String getRateSource() { return rateSource; }
    public void setRateSource(String rateSource) { this.rateSource = rateSource; }

    public boolean isOfficial() { return isOfficial; }
    public void setOfficial(boolean official) { isOfficial = official; }

    public String getRateType() { return rateType; }
    public void setRateType(String rateType) { this.rateType = rateType; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
