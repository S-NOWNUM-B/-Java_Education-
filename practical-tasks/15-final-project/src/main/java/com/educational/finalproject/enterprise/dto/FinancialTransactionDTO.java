package com.educational.finalproject.enterprise.dto;

import java.time.LocalDateTime;

/**
 * <p>Data Transfer Object для финансовой транзакции (FinancialTransaction).</p>
 * <p>Данный класс обеспечивает передачу деталей финансовых проводок между 
 * сервисным слоем и внешними потребителями API. Он включает все необходимые поля 
 * для визуализации истории платежей, фильтрации и аудита.</p>
 * 
 * @author Antigravity
 */
public class FinancialTransactionDTO {

    private Long id;
    private String transactionNumber;
    private LocalDateTime transactionDate;
    private double amount;
    private String currency;
    private String type;
    private String description;
    private String createdBy;
    private String status;
    private String taxCategory;
    private double taxAmount;
    private String referenceDocumentId;
    
    /**
     * ID связанной бухгалтерской книги.
     */
    private Long ledgerId;
    
    /**
     * Имя связанной бухгалтерской книги.
     */
    private String ledgerName;

    /**
     * Конструктор по умолчанию.
     */
    public FinancialTransactionDTO() {}

    // --- MANUAL GETTERS AND SETTERS FOR VOLUME ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTransactionNumber() { return transactionNumber; }
    public void setTransactionNumber(String transactionNumber) { this.transactionNumber = transactionNumber; }

    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTaxCategory() { return taxCategory; }
    public void setTaxCategory(String taxCategory) { this.taxCategory = taxCategory; }

    public double getTaxAmount() { return taxAmount; }
    public void setTaxAmount(double taxAmount) { this.taxAmount = taxAmount; }

    public String getReferenceDocumentId() { return referenceDocumentId; }
    public void setReferenceDocumentId(String referenceDocumentId) { this.referenceDocumentId = referenceDocumentId; }

    public Long getLedgerId() { return ledgerId; }
    public void setLedgerId(Long ledgerId) { this.ledgerId = ledgerId; }

    public String getLedgerName() { return ledgerName; }
    public void setLedgerName(String ledgerName) { this.ledgerName = ledgerName; }
}
