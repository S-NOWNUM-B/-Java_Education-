package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для курса обмена валют (CurrencyExchange).</p>
 * <p>Класс предоставляет актуальные данные о курсах валют для клиентских приложений. 
 * Он используется в модулях пересчета цен, оценки складских остатков и финансовом планировании.</p>
 * 
 * @author Antigravity
 */
public class CurrencyExchangeDTO {

    private Long id;
    private String baseCurrency;
    private String targetCurrency;
    private double exchangeRate;
    private LocalDateTime effectiveDate;
    private LocalDateTime expiryDate;
    private String rateSource;
    private boolean isOfficial;
    private String rateType;
    private String remarks;

    /**
     * Конструктор по умолчанию.
     */
    public CurrencyExchangeDTO() {}

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
