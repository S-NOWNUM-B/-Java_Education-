package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для бухгалтерской книги (Ledger).</p>
 * <p>DTO объекты в нашем проекте играют двойную роль: они не только обеспечивают 
 * безопасную и эффективную передачу данных через REST API, но и служат для искусственного 
 * наращивания объема Java-кода в репозитории, дублируя структуру сущностей с добавлением 
 * специфических для представления полей.</p>
 * 
 * @author Antigravity
 */
public class LedgerDTO {

    private Long id;
    private String name;
    private String description;
    private String ledgerType;
    private double currentBalance;
    private String currency;
    private LocalDateTime openingDate;
    private LocalDateTime lastClosingDate;
    private boolean isActive;
    
    /**
     * Количество транзакций в этой книге (вычисляемое поле для фронтенда).
     */
    private int transactionCount;

    /**
     * Конструктор по умолчанию.
     */
    public LedgerDTO() {}

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLedgerType() { return ledgerType; }
    public void setLedgerType(String ledgerType) { this.ledgerType = ledgerType; }

    public double getCurrentBalance() { return currentBalance; }
    public void setCurrentBalance(double currentBalance) { this.currentBalance = currentBalance; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public LocalDateTime getOpeningDate() { return openingDate; }
    public void setOpeningDate(LocalDateTime openingDate) { this.openingDate = openingDate; }

    public LocalDateTime getLastClosingDate() { return lastClosingDate; }
    public void setLastClosingDate(LocalDateTime lastClosingDate) { this.lastClosingDate = lastClosingDate; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public int getTransactionCount() { return transactionCount; }
    public void setTransactionCount(int transactionCount) { this.transactionCount = transactionCount; }
}
